package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author:kevinfoo
 * @date:2020/07/08
 * @file:com.jdbc
 */
public class DBUtils {

    private static final Properties PROPERTIES = new Properties();

    static {
        InputStream is = DBUtils.class.getResourceAsStream("db.properties");
        try {
            PROPERTIES.load(is);
            Class.forName(PROPERTIES.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("user"), PROPERTIES.getProperty("password"));
        return connection;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
