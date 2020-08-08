package com.spring.service.impl;

import com.spring.dao.IAccountDao;
import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：
 基于注解的账号service类
 * @author Hartley
 * date： 2020/8/4
 */
@Service("accountServiceAnno")
public class AccountServiceAnnonation implements IAccountService {
    //通过注入的方式
    @Autowired
    @Qualifier("accountDaoAnno")
    private IAccountDao accountDao;

    public void transfer(Integer src, Integer dst, Float money) {

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
