package science.freeabyss.hulk.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by abyss on 07/15/16.
 */
public class PrearedStatementUtil {

    private Connection conn;
    private PreparedStatement prepStatement;

    public PrearedStatementUtil(Connection conn) {
        this.conn = conn;
    }


    /**
     * 查询
     *
     * @param sql
     * @return
     */
    public ResultSet select(String sql, Object... params) {
        ResultSet resultSet = null;
        try {
            prepStatement = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    prepStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = prepStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 添加 删除 修改
     *
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql, Object... params) {
        int resultSet = 0;
        try {
            prepStatement = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    prepStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int update(String sql, List<Object[]> params) {

        int result = 0;
        try {
            conn.setAutoCommit(false);
            prepStatement = conn.prepareStatement(sql);
            if (params != null) {
                for (Object[] x : params) {
                    for (int i = 0; i < x.length; i++) {
                        prepStatement.setObject(i + 1, x[i]);
                    }
                    result += prepStatement.executeUpdate(sql);
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        } finally {
            close();
        }
        return result;
    }

    public void close() {
        try {
            if (prepStatement != null) {
                prepStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
