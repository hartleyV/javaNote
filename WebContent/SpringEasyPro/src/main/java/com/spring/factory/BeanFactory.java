package com.spring.factory;

import com.spring.pojo.Account;
import com.spring.service.IAccountService;
import com.spring.utils.TransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
    用来创建Servcie代理对象的factory
 * @author Hartley
 * date： 2020/8/8
 */
@Component("beanFactory")
public class BeanFactory {
    @Resource(name = "transactionAccountService")
    private IAccountService accountService;
    @Resource(name="transactionManager")
    private TransactionManager transactionManager;

    //获取service的代理对象
    @Bean("proxyAccountService")
    public IAccountService getAccountService(){
        System.out.println("动态代理中...");
        return (IAccountService)Proxy.newProxyInstance
                (accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 条件事务支持！！
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnVal = null;
                        try{
                            //开启事务
                            transactionManager.beginTransaction();
                            //执行操作
                            returnVal = method.invoke(accountService,args);
                            //提交事务
                            transactionManager.commit();
                            //返回结果
                            return  returnVal;
                        }catch(Exception e){
                            //回滚操作
                            transactionManager.rollBack();
                            throw new RuntimeException("操作失败");
                        }finally {
                            //释放资源
                            transactionManager.release();
                        }

                    }
                });

    }
}
