package com.spring.proxydemo.baseCglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//基于子类**动态代理**
//消费者买产品
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        //producer.saleProduct(100);

        //创建代理对象
        Producer proxyProducer = (Producer)Enhancer.create(
                Producer.class, new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object returnVal = null;
                Float money = (Float)args[0];

                //判断当前执行的方法
                if ("saleProduct".equals(method.getName())){
                    //中间商赚差价，，，，，
                    returnVal = method.invoke(producer,money * 0.5f);
                }
                return returnVal;
            }
        });
        proxyProducer.saleProduct(100);

    }
}
