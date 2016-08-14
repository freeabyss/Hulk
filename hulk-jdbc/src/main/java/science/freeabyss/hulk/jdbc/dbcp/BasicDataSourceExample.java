package science.freeabyss.hulk.jdbc.dbcp;

/**
 * Created by abyss on 08/14/16.
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.commons.dbcp2.BasicDataSource;
import science.freeabyss.hulk.jdbc.PropertiesUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//
// Here are the dbcp-specific classes.
// Note that they are only used in the setupDataSource
// method. In normal use, your classes interact
// only with the standard JDBC API
//

//
// Here's a simple example of how to use the BasicDataSource.
//

//
// Note that this example is very similar to the PoolingDriver
// example.

//
// To compile this example, you'll want:
//  * commons-pool-2.3.jar
//  * commons-dbcp-2.1.jar
// in your classpath.
//
// To run this example, you'll want:
//  * commons-pool-2.3.jar
//  * commons-dbcp-2.1.jar
//  * commons-logging-1.2.jar
// in your classpath.
//
//
// Invoke the class using two arguments:
//  * the connect string for your underlying JDBC driver
//  * the query you'd like to execute
// You'll also want to ensure your underlying JDBC driver
// is registered.  You can use the "jdbc.drivers"
// property to do this.
//
// For example:
//  java -Djdbc.drivers=org.h2.Driver \
//       -classpath commons-pool2-2.3.jar:commons-dbcp2-2.1.jar:commons-logging-1.2.jar:h2-1.3.152.jar:. \
//       ComboPooledDataSourceExample \
//       "jdbc:h2:~/test" \
//       "SELECT 1"
//
public class BasicDataSourceExample {

    private static BasicDataSource dataSource;

    static {
        initDataSource();
    }

    private static void initDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(PropertiesUtil.getString("jdbc.driver"));
        dataSource.setUrl(PropertiesUtil.getString("jdbc.url"));
        dataSource.setUsername(PropertiesUtil.getString("jdbc.username"));
        dataSource.setPassword(PropertiesUtil.getString("jdbc.password"));
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void shutdownDataSource(DataSource ds) throws SQLException {
        dataSource.close();
    }
}