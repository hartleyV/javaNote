package com.spring.couple_demo.factory;

import com.spring.couple_demo.service.IAccountService;
import com.spring.couple_demo.service.impl.AccountService;

/**
 * description：
 静态方法的工厂
 * @author Hartley
 * date： 2020/8/1
 */
public class StaticFactory {
    //【静态方法】生产AccountService
    public static IAccountService getAccountService(){
        return  new AccountService();
    }
}
