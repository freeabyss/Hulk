package science.freeabyss.thing.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by abyss on 07/15/16.
 */
public class ConnectionUtil {
    private static Properties pro;

    static {
        try {
            pro = new Properties();
            pro.load(ConnectionUtil.class.getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            String driver = pro.getProperty("jdbc.driver");
            String url = pro.getProperty("jdbc.url");
            String username = pro.getProperty("jdbc.username");
            String password = pro.getProperty("jdbc.password");
            String maxActive = pro.getProperty("jdbc.maxActive");

            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
