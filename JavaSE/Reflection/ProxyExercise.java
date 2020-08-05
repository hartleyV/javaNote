package Reflection;

import org.junit.Test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 【1】静态代理
 * 【2】动态代理（利用反射）==目的是通用性，可以用一个代理类动态代理多个被代理类
        1：用被代理的运行时类NikeFactory，创建代理类Proxy的对象==Proxy.newProxyInstance
        2：调用代理类Proxy的方法时，会动态调用被代理类NikeFactory的同名方法==InvocationHandler接口的invoke方法
 * @author Hartley
 * @create 2020/5/20
 */

//=============【1】静态代理========================
//代理类和被代理类需要都实现该接口
interface ClothFactory{
    public void produce();
    public String info(String data);
}
//代理类
class ProxyFactory implements ClothFactory{
    //成员变量为被代理类，利用多态，同时实现一个接口
    ClothFactory factory;
    public ProxyFactory(ClothFactory factory){
        this.factory = factory;
    }
    @Override
    public void produce() {
        System.out.println("代理工厂做衣服ing");
        factory.produce();
    }

    @Override
    public String info(String data) {
        return null;
    }
}

//=============【2】动态代理========================
class ProxyFactory2{
    //（1）：通过该静态方法，被代理类对象obj-->动态返回代理类Proxy的对象
    public static Object getProxyInstance(Object obj){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new MyInvocationHandler(obj));
        //获取接口是为了得到代理类和被代理类的同名方法
    }
}
    //(2)：用代理类的对象动态的调用被代理类中同名方法
class MyInvocationHandler implements InvocationHandler{
    private Object obj;//被代理类对象
    public MyInvocationHandler(Object obj){
        this.obj = obj;
    }

    //代理类Proxy对象调用方法a时，会自动运行invoke（被代理类要执行的方法也在里面）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理类搞事情ing~");
        System.out.println("接下来调用被代理类方法");
        //method为代理类要调用的方法（也是被代理类的）
        return method.invoke(obj,args);
    }
}

//被代理类
class NikeFactory implements ClothFactory{

    @Override
    public void produce() {
        System.out.println("Nike挂标");
    }

    @Override
    public String info(String data) {
        return data;
    }
}
public class ProxyExercise {
    //静态代理
    @Test
    public void staticProxyTest(){
        NikeFactory nike = new NikeFactory();
        ProxyFactory proxy = new ProxyFactory(nike);//编译时已经确定--静态代理
        proxy.produce();//用代理类执行方法
    }

    //动态代理
    @Test
    public void dynamicProxyTest(){
        NikeFactory nike = new NikeFactory();//创建被代理对象
        ClothFactory factory = (ClothFactory) ProxyFactory2.getProxyInstance(nike);//返回值类型为对应方法的接口类型

        String info = factory.info("hhahah");//调用代理类proxy的方法时，可以自动调用被代理类的方法
        System.out.println(info);
    }

}
