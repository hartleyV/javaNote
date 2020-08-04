package com.spring.dao.impl;

import com.spring.dao.IAccountDao;
import com.spring.pojo.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * description：
通过注解实现的账号dao接口实现类
 * @author Hartley
 * date： 2020/8/4
 */

@Repository("accountDaoAnno")
public class AccountDaoAnnotation implements IAccountDao {

    @Autowired//自动注入不需要set方法了
    private QueryRunner runner;

    //查询所有账户
    public List<Account> findAllAccount() {
        try {

            return runner.query("select * from account",
                    new BeanListHandler<Account>(Account.class));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Account findAccountById(Integer id) {
        try {

            return runner.query("select * from account where id= ?",
                    new BeanHandler<Account>(Account.class),id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void saveAccount(Account account) {

        try {

            runner.update("insert into account(name,money)values(?,?)",
                    account.getName(),account.getMoney());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateAccount(Account account) {
        try {

            runner.update("update account set name=? ,money=? where id=?",
                    account.getName(),account.getMoney(),account.getId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAccountById(Integer id) {
        try {

            runner.update("delete from account where id=?",id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
