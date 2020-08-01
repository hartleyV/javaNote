package com.spring.couple_demo.factory;

import com.spring.couple_demo.service.IAccountService;
import com.spring.couple_demo.service.impl.AccountService;

/**
 * description：
 模拟用Spring通过工厂类获取对象
 * @author Hartley
 * date： 2020/8/1
 */
public class FactoryInstance {
    //生产AccountService
    public IAccountService getAccountService(){
        return  new AccountService();
    }
}
