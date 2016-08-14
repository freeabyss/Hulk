package science.freeabyss.hulk.jdbc;

import science.freeabyss.hulk.jdbc.simple.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by abyss on 07/15/16.
 */
public class PrearedStatementUtil {
    /**
     * 查询
     *
     * @param sql
     * @return
     */
    public static ResultSet select(String sql, Object... params) {
        ResultSet resultSet = null;
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            resultSet = statement.executeQuery();
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
    public static int update(String sql, Object... params) {
        int resultSet = 0;
        try (Connection con = ConnectionUtil.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            resultSet = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int commit(String sql, List<Object[]> params) {
        Connection con = null;
        PreparedStatement prep = null;
        int result = 0;
        try {
            con = ConnectionUtil.getConnection();
            con.setAutoCommit(false);
            prep = con.prepareStatement(sql);
            if (params != null) {
                for (Object[] x : params) {
                    for (int i = 0; i < x.length; i++) {
                        prep.setObject(i + 1, x[i]);
                    }
                    result += prep.executeUpdate(sql);
                }
            }
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (prep != null) {
                    prep.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
