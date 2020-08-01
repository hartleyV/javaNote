package com.spring.couple_demo.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description：

 创建Bean对象的工厂
    Bean（可重用组件）
 JavaBean:用java语言编写的可重用组件（不只是实体类，还包括业务层、持久层）

 1：通过一个配置文件来配置Service和dao（全限定类名==唯一标识作为key
 2：读取配置文件中的内容，然后反射造对象
 * @author Hartley
 * date： 2020/7/31
 */
public class BeanFactory {
    private static Properties props;//定义属性文件对象
    //定义map容器盛放创建过的对象
    private static Map<String,Object> beans;

    //使用静态代码块初始化静态成员对象(一次性把所有对象创建完，并存到Map集合中--为了单例！！！
    static {
        //=============初始化Map对象===========================
        beans = new HashMap<String, Object>();


        //=============初始化属性对象===========================
        props = new Properties();
        InputStream in = BeanFactory.class.getClassLoader()
                .getResourceAsStream("bean.properties");
        try {
            props.load(in);//实例化对象

            Enumeration keys = props.keys();//获取属性文件所有key
            //遍历枚举
            while (keys.hasMoreElements()){
                String key = (String)keys.nextElement();
                //根据key获取全限定类名的value
                String beanPath = props.getProperty(key);
                //用反射创建对象
                Object bean = Class.forName(beanPath).newInstance();

                //存到map中
                beans.put(key,bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化配置文件失败");
        }


    }

    /**
     * 根据Bean名称获取bean对象
     */
    public static Object getBean(String beanName){
        Object bean = beans.get(beanName);



        /*
        String beanPath = props.getProperty(beanName);//读取全限定类名
        try {
            //System.out.println(beanPath);

            bean = Class.forName(beanPath).newInstance();
            //反射创建实例化对象，表示每次会调用默认构造方法创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        return bean;
    }

}
