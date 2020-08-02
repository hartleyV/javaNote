package com.spring.couple_demo.service.impl;

import com.spring.couple_demo.dao.IAccountDao;
import com.spring.couple_demo.service.IAccountService;
import com.spring.couple_demo.factory.BeanFactory;
import org.springframework.stereotype.Component;

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

public class AccountService implements IAccountService {

    //private IAccountDao accountDao = new AccountDao();//应当避免直接用new(会error错误)
    //private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    //适合注入的方式：相对稳定的变量
    private String name;
    private Integer age;
    private Date birthday;

    private String[] strs;
    private List<String> lists;
    private Map<String,Integer> map;
    private Properties props;


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
        System.out.println(Arrays.toString(strs));
        System.out.println(lists);
        System.out.println(map);
        System.out.println(props);

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
