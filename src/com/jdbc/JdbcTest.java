package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.注册驱动 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得链接
        String url = "jdbc:mysql://localhost:3306/chapter01?serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);

        if (connection != null) {
            System.out.println("连接到数据库啦！");
        } else {
            System.out.println("连接失败！");
        }

        // 3.获得执行SQL语句的对象
        Statement statement = connection.createStatement();

        // 4.编写SQL语句，执行SQL语句
        String sql = "insert into student (sname, age, course) values ('lidama', 12, 'Ruby')";
        int result = statement.executeUpdate(sql);

        // 5.处理结果
        if (result == 1) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }

        // 6.释放资源
        statement.close();
        connection.close();

    }

}
