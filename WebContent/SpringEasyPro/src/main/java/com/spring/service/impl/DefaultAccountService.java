package com.spring.service.impl;

import com.spring.dao.IAccountDao;
import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description：
    账户业务层的实现类
 * @author Hartley
 * date： 2020/8/3
 */
public class DefaultAccountService implements IAccountService {



    //通过set方式注入初始化该dao类
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccountById(Integer id) {
        accountDao.deleteAccountById(id);
    }
}
