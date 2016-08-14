package science.freeabyss.hulk.jdbc.simple;

import science.freeabyss.hulk.jdbc.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by abyss on 07/15/16.
 */
public class ConnectionUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        initDriver();

    }

    private static void initDriver() {
        String driver = PropertiesUtil.getString("jdbc.driver");
        url = PropertiesUtil.getString("jdbc.url");
        username = PropertiesUtil.getString("jdbc.username");
        password = PropertiesUtil.getString("jdbc.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
