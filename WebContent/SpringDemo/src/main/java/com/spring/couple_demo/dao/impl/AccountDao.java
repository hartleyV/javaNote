package com.spring.couple_demo.dao.impl;

import com.spring.couple_demo.dao.IAccountDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * description：
 *账户持久层的实现类
 * @author Hartley
 * date： 2020/7/31
 */

@Repository
public class AccountDao implements IAccountDao {
    public void saveAccount() {
        System.out.println("dao1保存了账户！");
    }
}
