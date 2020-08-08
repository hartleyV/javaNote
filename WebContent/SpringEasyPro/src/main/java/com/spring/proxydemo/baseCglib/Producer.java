package com.spring.proxydemo.baseCglib;

/**
 * description：
    生产者（被代理类
 * @author Hartley
 * date： 2020/8/8
 */
public class Producer {
    public  void saleProduct(float money){
        System.out.println("工厂卖产品获得:" + money);
    }

    public void afterService(float money){
        System.out.println("工厂售后获得:" + money);
    }
}
