package test;

import com.spring.config.SpringConfiguration;
import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringJUnitTest {

    @Autowired()
    @Qualifier("proxyAccountService")//设置测试的Service
    IAccountService accountService;


    @Test
    public void transferTest(){

        accountService.transfer(1,4,500f);
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
