package science.freeabyss.hulk.jdbc.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 简单实现数据库连接池
 * Created by abyss on 08/14/16.
 */

public class SimpleConnectionPool {
    private static LinkedList m_notUsedConnection = new LinkedList();
    private static HashSet m_usedUsedConnection = new HashSet();
    private static String m_url = "";
    private static String m_user = "";
    private static String m_password = "";
    private static int m_maxConnect = 3;
    static private long m_lastClearClosedConnection = System.currentTimeMillis();
    public static long CHECK_CLOSED_CONNECTION_TIME = 5000; // 5秒

    static {
        try {
            initDriver();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public SimpleConnectionPool(String url, String user, String password) {
        m_url = url;
        m_user = user;
        m_password = password;
    }

    private static void initDriver() throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public static synchronized Connection getConnection() {
        // 关闭清除多余的连接
        clearClosedConnection();

        // 寻找空闲的连接
        while (m_notUsedConnection.size() > 0) {
            try {
                Connection con = (Connection) m_notUsedConnection.removeFirst();

                if (con.isClosed()) {
                    continue;
                }
                m_usedUsedConnection.add(con);
                return con;
            } catch (SQLException e) {
            }
        }

        // 没有找到，建立一些新的连接以供使用
        int newCount = getIncreasingConnectionCount();
        LinkedList list = new LinkedList();
        Connection con = null;

        for (int i = 0; i < newCount; i++) {
            con = getNewConnection();
            if (con != null) {
                list.add(con);
            }
        }

        // 没有成功建立连接，访问失败
        if (list.size() == 0)
            return null;

        // 成功建立连接，使用的加入used队列，剩下的加入notUsed队列
        con = (Connection) list.removeFirst();
        m_usedUsedConnection.add(con);
        m_notUsedConnection.addAll(list);
        list.clear();

        return con;
    }

    private static Connection getNewConnection() {
        try {
            return DriverManager.getConnection(m_url, m_user, m_password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    static synchronized void pushConnectionBackToPool(Connection con) {
        boolean exist = m_usedUsedConnection.remove(con);
        if (exist) {
            m_notUsedConnection.addLast(con);
        }
    }

    public static int close() {
        int count = 0;

        Iterator iterator = m_notUsedConnection.iterator();
        while (iterator.hasNext()) {
            try {
                ((Connection) iterator.next()).close();
                count++;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        m_notUsedConnection.clear();

        iterator = m_usedUsedConnection.iterator();
        while (iterator.hasNext()) {
            try {
                ((Connection) iterator.next()).close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        m_usedUsedConnection.clear();

        return count;
    }

    private static void clearClosedConnection() {
        long time = System.currentTimeMillis();

        // 时间不合理，没有必要检查
        if (time < m_lastClearClosedConnection) {
            time = m_lastClearClosedConnection;
            return;
        }

        // 时间太短，没有必要检查
        if (time - m_lastClearClosedConnection < CHECK_CLOSED_CONNECTION_TIME) {
            return;
        }

        m_lastClearClosedConnection = time;

        // 开始检查没有使用的Connection
        Iterator iterator = m_notUsedConnection.iterator();
        while (iterator.hasNext()) {
            Connection con = (Connection) iterator.next();

            try {
                if (con.isClosed()) {
                    iterator.remove();
                }
            } catch (SQLException e) {
                iterator.remove();
            }
        }

        // 清除多余的Connection
        int decrease = getDecreasingConnectionCount();

        while (decrease > 0 && m_notUsedConnection.size() > 0) {
            Connection con = (Connection) m_notUsedConnection.removeFirst();

            try {
                con.close();
            } catch (SQLException e) {

            }
            decrease--;
        }
    }

    public static int getIncreasingConnectionCount() {
        int count = 1;
        count = getConnectionCount() / 4;

        if (count < 1)
            count = 1;

        return count;
    }

    public static int getDecreasingConnectionCount() {
        int count = 0;

        if (getConnectionCount() > m_maxConnect) {
            count = getConnectionCount() - m_maxConnect;
        }

        return count;
    }

    public static synchronized int getNotUsedConnectionCount() {
        return m_notUsedConnection.size();
    }

    public static synchronized int getUsedConnectionCount() {
        return m_usedUsedConnection.size();
    }

    public static synchronized int getConnectionCount() {
        return m_notUsedConnection.size() + m_usedUsedConnection.size();
    }

}