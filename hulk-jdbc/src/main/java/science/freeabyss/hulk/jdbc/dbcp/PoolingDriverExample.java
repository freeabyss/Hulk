package science.freeabyss.hulk.jdbc.dbcp;
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

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import science.freeabyss.hulk.jdbc.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//
// Here are the dbcp-specific classes.
// Note that they are only used in the setupDriver
// method. In normal use, your classes interact
// only with the standard JDBC API
//

//
// Here's a simple example of how to use the PoolingDriver.
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
//       PoolingDriverExample \
//       "jdbc:h2:~/test" \
//       "SELECT 1"
//
public class PoolingDriverExample {

    static {
        initDataSource();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:apache:commons:dbcp:example");
    }

    public static void initDataSource() {
        try {
            //
            // First, we'll create a ConnectionFactory that the
            // pool will use to create Connections.
            // We'll use the DriverManagerConnectionFactory,
            // using the connect string passed in the command line
            // arguments.
            //
            PropertiesUtil.init("dbcp.properties");
            Class.forName(PropertiesUtil.getString("dbcp.driver"));

            ConnectionFactory connFactory = new DriverManagerConnectionFactory(
                    PropertiesUtil.getString("dbcp.url"),
                    PropertiesUtil.getString("dbcp.username"),
                    PropertiesUtil.getString("dbcp.password"));

            //
            // Next, we'll create the PoolableConnectionFactory, which wraps
            // the "real" Connections created by the ConnectionFactory with
            // the classes that implement the pooling functionality.
            //
            PoolableConnectionFactory poolFactory = new PoolableConnectionFactory(connFactory, null);

            //
            // Now we'll need a ObjectPool that serves as the
            // actual pool of connections.
            //
            // We'll use a GenericObjectPool instance, although
            // any ObjectPool implementation will suffice.
            //
            ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolFactory);

            // Set the factory's pool property to the owning pool
            poolFactory.setPool(connectionPool);

            //
            // Finally, we create the PoolingDriver itself...
            //
            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

            //
            // ...and register our pool with it.
            //
            driver.registerPool("example", connectionPool);

            //
            // Now we can just use the connect string "jdbc:apache:commons:dbcp:example"
            // to access our pool of Connections.
            //
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void shutdownDriver() throws Exception {
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.closePool("example");
    }
}