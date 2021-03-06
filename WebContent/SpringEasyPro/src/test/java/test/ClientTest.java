package test;

import com.spring.config.SpringConfiguration;
import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * description：
使用junit测试~账户
 * @author Hartley
 * date： 2020/8/3
 */


public class ClientTest {
    ApplicationContext ac;
    IAccountService accountService;

    @Before
    public void init(){
        //通过xml获取核心容器
        //ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取业务层对象
        //accountService = (IAccountService)ac.getBean("accountService");

        //通过注解xml获取核心容器,取业务层对象
//        ac = new ClassPathXmlApplicationContext("beanAnnonation.xml");
//        accountService = (IAccountService)ac.getBean("accountServiceAnno");

        //通过注解
        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = (IAccountService)ac.getBean("accountServiceAnno");

    }

    @Test
    public void findAllTest(){

        List<Account> accounts = accountService.findAllAccount();
        for (Account account:accounts){
            System.out.println(account);
        }

        //对应sql select * from account
    }

    @Test
    public void findByIdTest(){
       Account account = accountService.findAccountById(2);
        System.out.println(account);
        //对应sql select * from account where id = ?
    }

    @Test
    public void saveAccountTest(){
        Account account = new Account("Hartley4_anno",999999f);
        accountService.saveAccount(account);

        //对应sql insert into account(name,money)values(?,?)
    }

    @Test
    public void updateAccountTest(){
        Account account = accountService.findAccountById(4);
        account.setMoney(666f);
        accountService.updateAccount(account);

        //对应sql update account set name=? , money=? where id=?

    }

    @Test
    public void deleteAccountByIdTest(){
        accountService.deleteAccountById(3);

        //对应sql delete from account where id = ?
    }
}
