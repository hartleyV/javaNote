package com.demo.mybatis;

import com.demo.mybatis.dao.IAccountDAO;
import com.demo.mybatis.dao.IUserDAO;
import com.demo.mybatis.pojo.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/7/29
 */
public class AccountTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDAO accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDAO.class);
    }

    @After
    public void destory() throws Exception{
        session.commit();//????
        session.close();
        in.close();
    }


    //遍历account信息
    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.findAll();

        for (Account account:accounts){
            System.out.println("---【每个账号信息】---");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
