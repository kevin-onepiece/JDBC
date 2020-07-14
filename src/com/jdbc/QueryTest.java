package com.jdbc;

import java.sql.*;

public class QueryTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chapter01?serverTimezone=UTC", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();


    }

}
