package com.spring.service.impl;

import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/8
 */
@Service("accountService_aop")
public class AopAccountService implements IAccountService {
    public void transfer(Integer src, Integer dst, Float money) {

    }

    public List<Account> findAllAccount() {
        return null;
    }

    public Account findAccountById(Integer id) {
        return null;
    }

    public void saveAccount(Account account) {
        System.out.println("存储中ing。。");
        int n = 1/0;
    }

    public void updateAccount(Account account) {

    }

    public void deleteAccountById(Integer id) {

    }
}
