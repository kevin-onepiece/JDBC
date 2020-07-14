package com.person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:kevinfoo
 * @date:2020/07/08
 * @file:com.person dao：就是单独对一张表的增删改查所有的功能都在这个类里
 */
public class PersonDaoImpl {

    // 添加
    public int add(Person person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into Person (name, age, bornDate, email, address) values (?, ?, ?, ?, ?);";
        connection = DBUtils.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setDate(3, DateUtils.utilToSql(person.getBornDate()));
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getAddress());
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, null);
        }
        return 0;
    }

    // 修改
    public int update(Person person) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update person set name = ?, age = ?, borndate = ?, email = ?, address = ? where id = ?; ";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setDate(3, DateUtils.utilToSql(person.getBornDate()));
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setInt(6, person.getId());
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, null);
        }
        return 0;
    }

    // 删除
    public int delete(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from person where id = ?;";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, null);
        }
        return 0;
    }

    // 查单个
    public Person query(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from person where id = ?;";
        Person person = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int pid = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                person = new Person(pid, name, age, bornDate, email, address);
            }
            return person;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

    public Person queryName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from person where name = ?;";
        Person person = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int pid = resultSet.getInt("id");
                String pname = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                person = new Person(pid, pname, age, bornDate, email, address);
            }
            return person;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

    // 查找所有
    public List<Person> queryAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Person person = null;
        ResultSet resultSet = null;
        ArrayList<Person> personList = new ArrayList<>();
        String sql = "select * from person;";

        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Date bornDate = resultSet.getDate("bornDate");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                person = new Person(id, name, age, bornDate, email, address);

                personList.add(person);

            }
            return personList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

}
