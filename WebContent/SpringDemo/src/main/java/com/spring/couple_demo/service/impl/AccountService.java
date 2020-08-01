package com.spring.couple_demo.service.impl;

import com.spring.couple_demo.dao.IAccountDao;
import com.spring.couple_demo.service.IAccountService;
import com.spring.couple_demo.factory.BeanFactory;

import java.util.Date;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/31
 */

/**
 * 账户的业务层实现类：与持久层DAO交互
 */
public class AccountService implements IAccountService {

    //private IAccountDao accountDao = new AccountDao();//应当避免直接用new(会error错误)
    //private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    //适合注入的方式：相对稳定的变量
    private String name;
    private Integer age;
    private Date birthday;


    private  int i=0;
    public  AccountService(){
        System.out.println("accountService对象创建了");
    }

    public  AccountService(String name,Integer age,Date birthday){
        this.name =name;
        this.age = age;
        this.birthday = birthday;
    }
    public void saveAccount() {
        System.out.println("accountService对象执行了saveAccount");

        /*
        accountDao.saveAccount();
        i++;
        System.out.println(i);

         */
    }

    public void init(){
        System.out.println("init()");
    }
    public void destory(){
        System.out.println("destory()");
    }

    @Override
    public String toString() {
        return "AccountService{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
