package com.spring.service.impl;

import com.spring.dao.IAccountDao;
import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import com.spring.utils.ConnectionUtils;
import com.spring.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description：
    账户业务层的实现类
        功能：CRUD、转账
 * @author Hartley
 * date： 2020/8/3
 */

@Service("defaultAccountService")
public class DefaultAccountService implements IAccountService {

    @Resource(name="accountDaoAnno")
    private IAccountDao accountDao;
    //通过set方式注入初始化该dao类
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //转账（根据名称查询对应账户，转出账户减钱 转入价钱，
    public void transfer(Integer src, Integer dst, Float money) {

        Account donator = accountDao.findAccountById(src);
        Account acceptor = accountDao.findAccountById(dst);


        if (donator.getMoney()< money)
            throw new RuntimeException(donator.getName()+" 先生，您的余额不足！！");

        donator.setMoney(donator.getMoney()-money);
        acceptor.setMoney(acceptor.getMoney()+money);


        accountDao.updateAccount(acceptor);
        //int i = 1/0;//异常出现呢？？？
        accountDao.updateAccount(donator);


        System.out.println(donator.getName()+" 成功向 "+acceptor.getName() +"转账"
                +money+"元，余额为 "+donator.getMoney());
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccountById(Integer id) {
        accountDao.deleteAccountById(id);
    }
}
