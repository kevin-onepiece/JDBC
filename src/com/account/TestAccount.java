package com.account;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.account
 */
public class TestAccount {

    public static void main(String[] args) {

        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.transfer("6002", "1234", "6003", 200);


    }

}
