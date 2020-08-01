package com.spring.couple_demo.dao.impl;

import com.spring.couple_demo.dao.IAccountDao;

/**
 * description：
 *账户持久层的实现类
 * @author Hartley
 * date： 2020/7/31
 */
public class AccountDao implements IAccountDao {
    public void saveAccount() {
        System.out.println("保存了账户！");
    }
}
