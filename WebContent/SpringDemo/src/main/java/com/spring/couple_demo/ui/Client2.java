package com.spring.couple_demo.ui;

import com.spring.couple_demo.dao.IAccountDao;
import com.spring.couple_demo.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**

 =======通过Spring的annonation配置IOC===============
 * @author Hartley
 * date： 2020/7/31
 */
public class Client2 {
    //首先获取Spring的IOC核心容器，并根据id获取对象
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beanAnnonation.xml");
        //2.根据Id获取Bean对象
        IAccountService accountService =
                (IAccountService)applicationContext.getBean("accountService2");

        IAccountService accountService2 =
                (IAccountService)applicationContext.getBean("accountService2");

        System.out.println("是否为单例？"+(accountService==accountService2));

        IAccountDao accountDao = applicationContext.getBean("accountDao",IAccountDao.class);

        accountService.saveAccount();

        ((ClassPathXmlApplicationContext)applicationContext).close();

    }


}
