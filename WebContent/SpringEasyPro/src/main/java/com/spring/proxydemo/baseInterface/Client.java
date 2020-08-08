package com.spring.proxydemo.baseInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//基于接口**动态代理**
//消费者买产品
public class Client {
    public static void main(String[] args) {
        final Producer producer = new Producer();
        //producer.saleProduct(100);

        //创建代理对象
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance
                (Producer.class.getClassLoader(),//被代理对象加载器
                Producer.class.getInterfaces(),//被代理对象接口
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

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
