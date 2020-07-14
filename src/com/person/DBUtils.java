package com.person;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author:kevinfoo
 * @date:2020/07/08
 * @file:com.person
 */
public class DBUtils {

    private final static Properties PROPERTIES = new Properties();

    static {
        InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            PROPERTIES.load(is);
            Class.forName(PROPERTIES.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("user"), PROPERTIES.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
