package com.account;

/**
 * @author:kevinfoo
 * @date:2020/07/11
 * @file:com.account
 */
public class AccountServiceImpl {

    public void transfer(String fromNo, String pwd, String toNo, double money) {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        // 2.组织完善业务功能
        // 2.1验证fromTo账号是否存在
        try {

            DBUtils.begin();
            Account account = accountDao.query(fromNo);
            if (account == null) {
                throw new RuntimeException("账号不存在啊");
            }

            // 2.2验证密码是否正确
            if (!account.getPassword().equals(pwd)) {
                throw new RuntimeException("密码不正确");
            }

            // 2.3验证账户金额是否足够
            if (account.getBalance() < money) {
                throw new RuntimeException("金额不足");
            }

            // 2.4验证toNo账号是否存在
            Account toAccount = accountDao.query(toNo);
            if (toAccount == null) {
                throw new RuntimeException("toNo账户不存在");
            }

            // 2.5扣除fromNo账号的钱
            account.setBalance(account.getBalance() - money);
            int update = accountDao.update(account);

//            int a = 10 / 0;

            // 2.6增加toNo账号的钱
            toAccount.setBalance(toAccount.getBalance() + money);
            accountDao.update(toAccount);

            System.out.println("转账成功");
            DBUtils.commit();
        } catch (RuntimeException e) {
            System.out.println("转账失败");
            DBUtils.rollback();
            e.printStackTrace();
        }
    }

}
