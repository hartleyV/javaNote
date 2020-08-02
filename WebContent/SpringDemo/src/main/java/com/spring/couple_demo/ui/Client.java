package com.spring.couple_demo.ui;

import com.spring.couple_demo.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description：
 模拟表现层，用来调用业务层

流程：表现层Client调用业务层AccountService，业务层调用持久层AccountDao
其中new关键字出现两次，表现当前程序耦合很强
    =======通过Spring的xml配置IOC===============
 * @author Hartley
 * date： 2020/7/31
 */
public class Client {
    //首先获取Spring的IOC核心容器，并根据id获取对象
    public static void main(String[] args) {
        /**/
        //1.获取核心容器对象(饿汉式ApplicationContext)
        ApplicationContext applicationContext =
                //new FileSystemXmlApplicationContext("bean.xml");
                new ClassPathXmlApplicationContext("bean.xml");
        //2.根据Id获取Bean对象
        IAccountService accountService =
                (IAccountService)applicationContext.getBean("accountService");
        //IAccountDao accountDao = applicationContext.getBean("accountDao",IAccountDao.class);

        accountService.saveAccount();
        System.out.println(accountService);

        //手动关闭IOC容器
        ((ClassPathXmlApplicationContext)applicationContext).close();



        /**
         * 【BeanFactory】与ApplicationContext区别（多例，单例
         */

        /*
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);

        IAccountService accountService =
                (IAccountService) factory.getBean("accountService");
        IAccountDao accountDao =
                (IAccountDao) factory.getBean("accountDao");

         */


    }

    public static void testRemoveCouple(){
        //IAccountService service = new AccountService();
        IAccountService service = null;
        for (int i=0;i<5;i++){
            //多例对象：被创建多次，效率低~
            //====此处是自定义的BeanFactory
           // service = (IAccountService)BeanFactory.getBean("accountService");

            System.out.println(service);
            service.saveAccount();
        }
    }
}
