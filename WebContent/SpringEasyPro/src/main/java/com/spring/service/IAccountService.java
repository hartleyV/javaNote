package com.spring.service;

import com.spring.pojo.Account;

import java.util.List;

/**
 * description：
    账户的业务层接口: 增删改查 （事务控制应写在此处！！
 * @author Hartley
 * date： 2020/8/3
 */
public interface IAccountService {

    /**
     * 转账操作
     *
     * @param src 谁转账
     * @param dst 转账到哪里
     */
    void transfer(Integer src, Integer dst, Float money);
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
