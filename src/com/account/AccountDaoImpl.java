package com.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.account
 */
public class AccountDaoImpl {

    // 1.增
    public int add() {
        return -1;
    }

    // 2.删
    public int delete() {
        return -1;
    }

    // 3.改
    public int update(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update account set password = ?, name = ?, balance = ? where cardNo = ?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getCardNo());
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null, preparedStatement, null);
        }
        return -1;
    }

    // 4.查单个
    public Account query(String cardNo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;
        String sql = "select * from account where cardNo = ?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardNo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cardNo1 = resultSet.getString("cardNo");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");
                account = new Account(cardNo1, password, name, balance);
            }
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.closeAll(null, preparedStatement, resultSet);
        }
        return null;
    }

    // 5.查所有
    public List<Account> queryAll() {
        return null;
    }

}
