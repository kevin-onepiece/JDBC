package com.account;

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
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

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
        Connection connection = threadLocal.get(); // 将当前线程中绑定的Connection对象，赋值给connection
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("user"), PROPERTIES.getProperty("password"));
                threadLocal.set(connection); // 把连接存入threadlocal
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 开启事务
    public static void begin() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // 提交事务
    public static void commit() {
        Connection connection = getConnection();
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(connection, null, null);
        }
    }

    // 回滚事务
    public static void rollback() {
        Connection connection = getConnection();
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(connection, null, null);
        }
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
