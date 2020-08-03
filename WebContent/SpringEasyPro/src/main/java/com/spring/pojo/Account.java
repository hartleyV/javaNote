package com.spring.pojo;

import java.io.Serializable;

/**
 * description：
    账户的实体类
 * @author Hartley
 * date： 2020/8/3
 */
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Float money;

    //默认构造器，用于其他，，
    public Account(){}
    public Account(String name, Float money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
