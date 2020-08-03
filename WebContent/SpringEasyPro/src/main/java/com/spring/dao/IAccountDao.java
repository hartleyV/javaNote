package com.spring.dao;

import com.spring.pojo.Account;

import java.util.List;

/**
 * description：
    账户持久层接口：

 * @author Hartley
 * date： 2020/8/3
 */
public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 根据id查询
     */
    Account findAccountById(Integer id);

    /**
     * 保存账户
     */
    void saveAccount(Account account);
    /**
     * 更新账户信息
     */
    void updateAccount(Account account);
    /**
     * 通过id删除账户
     */
    void deleteAccountById(Integer id);
}
