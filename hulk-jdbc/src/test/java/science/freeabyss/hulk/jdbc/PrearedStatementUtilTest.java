package science.freeabyss.hulk.jdbc;

import org.junit.Test;
import science.freeabyss.hulk.jdbc.c3p0.ComboPooledDataSourceExample;
import science.freeabyss.hulk.jdbc.dbcp.BasicDataSourceExample;
import science.freeabyss.hulk.jdbc.dbcp.PoolingDriverExample;
import science.freeabyss.hulk.jdbc.simple.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by abyss on 07/15/16.
 */
public class PrearedStatementUtilTest {
    static String sql = "select title from docEntity";
    static String[] fields = {"title"};

    @Test
    public void jdbc() {
        try {
            ResultSet set = new PrearedStatementUtil(ConnectionUtil.getConnection())
                    .select(sql);
            ResultSetUtil.print(set, fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void c3p0() {
        try {
            ResultSet set = new PrearedStatementUtil(ComboPooledDataSourceExample.getConnection())
                    .select(sql);
            ResultSetUtil.print(set, fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dbcpBasicDataSource() {
        try {
            ResultSet set = new PrearedStatementUtil(BasicDataSourceExample.getConnection())
                    .select(sql);
            ResultSetUtil.print(set, fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dbcpPoolingDriver() {
        try {
            ResultSet set = new PrearedStatementUtil(PoolingDriverExample.getConnection())
                    .select(sql);
            ResultSetUtil.print(set, fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
