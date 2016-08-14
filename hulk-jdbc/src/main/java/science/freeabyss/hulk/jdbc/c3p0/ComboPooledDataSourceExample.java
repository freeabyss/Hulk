package science.freeabyss.hulk.jdbc.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import science.freeabyss.hulk.jdbc.PropertiesUtil;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by abyss on 08/14/16.
 */
public class ComboPooledDataSourceExample {
    private static ComboPooledDataSource cpds;

    static {
        initDataSource();
    }

    private static void initDataSource() {
        cpds = new ComboPooledDataSource();
        try {
            PropertiesUtil.init("c3p0.properties");

            cpds.setDriverClass(PropertiesUtil.getString("c3p0.driver")); //loads the jdbc driver
            cpds.setJdbcUrl(PropertiesUtil.getString("c3p0.url"));
            cpds.setUser(PropertiesUtil.getString("c3p0.username"));
            cpds.setPassword(PropertiesUtil.getString("c3p0.password"));

            cpds.setMinPoolSize(PropertiesUtil.getInteger("c3p0.minPoolSize"));
            cpds.setMaxPoolSize(PropertiesUtil.getInteger("c3p0.maxPoolSize"));
            //当连接池中的连接耗尽的时候 c3p0 一次同时获取的连接数
            cpds.setAcquireIncrement(PropertiesUtil.getInteger("c3p0.acquireIncrement"));
            //定义在从数据库获取新连接失败后重复尝试的次数
            cpds.setAcquireRetryAttempts(PropertiesUtil.getInteger("c3p0.acquireRetryAttempts"));
            //两次连接中间隔时间，单位毫秒
            cpds.setAcquireRetryDelay(PropertiesUtil.getInteger("c3p0.acquireRetryDelay"));
            //连接关闭时默认将所有未提交的操作回滚
            cpds.setAutoCommitOnClose(PropertiesUtil.getBoolean("c3p0.autoCommitOnClose"));
            //当连接池用完时客户端调用 getConnection() 后等待获取新连接的时间，超时后将抛出 SQLException, 如设为 0 则无限期等待。单位毫秒
            cpds.setCheckoutTimeout(PropertiesUtil.getInteger("c3p0.checkoutTimeout"));
            //每120 秒检查所有连接池中的空闲连接。Default: 0
            cpds.setIdleConnectionTestPeriod(PropertiesUtil.getInteger("c3p0.idleConnectionTestPeriod"));
            //最大空闲时间,60 秒内未使用则连接被丢弃。若为 0 则永不丢弃。Default: 0
            cpds.setMaxIdleTime(PropertiesUtil.getInteger("c3p0.maxIdleTime"));
            //如果设为 true 那么在取得连接的同时将校验连接的有效性。Default: false
            cpds.setTestConnectionOnCheckin(PropertiesUtil.getBoolean("c3p0.testConnectionOnCheckin"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    public static synchronized Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
