package com.spring.couple_demo.service.impl;

import com.spring.couple_demo.dao.IAccountDao;
import com.spring.couple_demo.service.IAccountService;
import com.spring.couple_demo.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.*;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/31
 */

/**
 * 账户的业务层实现类：与持久层DAO交互
 * 以前xml配置
 （bean对象、作用范围-scope、生命周期init-methode:destory-methode、注入数据property--bean标签下）

 **现在通过注解实现：
 *      创建对象标签：Component
 *                  作用：把当前类放到IOC容器中
 *                  属性：
 *                      value：用来指定bean类的id

 *
 */
//@Component(value = "accountService2")
@Service
//@Scope(value="prototype")
public class AccountService2 implements IAccountService {

    /*
    @Autowired
    @Qualifier(value = "accountDao2")
     */
    @Resource(name = "accountDao2")
    private IAccountDao accountDao;

    public  AccountService2(){
        System.out.println("accountService2对象创建了");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }

    @PostConstruct
    public void init(){
        System.out.println("initialize this program");
    }
    @PreDestroy
    public void destory(){
        System.out.println("destory this program");
    }

}
