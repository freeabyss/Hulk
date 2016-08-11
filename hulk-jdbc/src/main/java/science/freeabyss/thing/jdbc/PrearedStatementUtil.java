package science.freeabyss.thing.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
