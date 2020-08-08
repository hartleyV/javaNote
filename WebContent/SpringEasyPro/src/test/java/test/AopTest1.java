package test;

import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
    初次测试Aop
 * date： 2020/8/8
 */
public class AopTest1 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean_aopAnno.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService_aop");

        as.saveAccount(new Account());
    }
}
