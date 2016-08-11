package science.freeabyss.thing.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import science.freeabyss.thing.mybatis.dao.StudentMapper;
import science.freeabyss.thing.mybatis.modul.PhoneNumber;
import science.freeabyss.thing.mybatis.modul.Student;
import science.freeabyss.thing.mybatis.typehandler.PhoneTypeHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by abyss on 06/13/16.
 */
public class MyBatisSqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    /**
     * 基于Java API 编程的方式实现
     *
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryByHand() {
        SqlSessionFactory sqlSessionFactory = null;

        DataSource dataSource = DataSourceFactory.getPooledDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.getTypeAliasRegistry().registerAlias("student", Student.class);
        configuration.getTypeHandlerRegistry().register(PhoneNumber.class, PhoneTypeHandler.class);

        configuration.addMapper(StudentMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }

    public static SqlSession openSession() {
        return getSqlSessionFactory().openSession();
    }

}
