package com.spring.couple_demo.dao.impl;

import com.spring.couple_demo.dao.IAccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository

public class AccountDao2 implements IAccountDao {
    public void saveAccount() {
        System.out.println("dao2保存了账户！");
    }
}
