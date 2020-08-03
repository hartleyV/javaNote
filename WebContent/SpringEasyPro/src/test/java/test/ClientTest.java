package test;

import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * description：
使用junit测试~账户
 * @author Hartley
 * date： 2020/8/3
 */
public class ClientTest {

    @Test
    public void findAllTest(){
        //获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据id获取业务层对象
        IAccountService accountService = (IAccountService)ac.getBean("accountService");
        List<Account> account = accountService.findAllAccount();
        System.out.println(account);
        //对应sql select * from account
    }

    @Test
    public void findByIdTest(){
        //获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService)ac.getBean("accountService");
       Account account = accountService.findAccountById(2);
        System.out.println(account);
        //对应sql select * from account where id = ?
    }

    @Test
    public void saveAccountTest(){
        //获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService)ac.getBean("accountService");
        Account account = new Account("Hartley2",999999f);
        accountService.saveAccount(account);

        //对应sql insert into account(name,money)values(?,?)
    }

    @Test
    public void updateAccountTest(){
        //获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService)ac.getBean("accountService");
        Account account = accountService.findAccountById(4);
        account.setMoney(666f);
        accountService.updateAccount(account);

        //对应sql update account set name=? , money=? where id=?

    }

    @Test
    public void deleteAccountByIdTest(){
        //获取核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService)ac.getBean("accountService");
        accountService.deleteAccountById(3);

        //对应sql delete from account where id = ?
    }
}
