package com.spring.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
    记录日志的工具类：用来打印日志
 * date： 2020/8/8
 */

@Component("logger")
@Aspect//表示是个切面类
public class Logger {
    @Pointcut("execution( * com.spring.service.impl.AopAccountService.*(..))")
    public void pc(){}//该方法可以获得切入点表达式
    /**
     * 前置通知：用来打印日志，在切入点方法执行前执行 before标签
     */
    @Before("pc()")
    public void printLog(){
        System.out.println("前置通知:打印日志啦");
    }

    /**
     * 后置通知：通知已经完毕---after-Returning标签
     */
    @AfterReturning("pc()")
    public void afterLog(){
        System.out.println("后置通知：通知已经完毕");
    }
    /**
     * 异常通知：通知出现异常啦---after-throwing标签
     */
    @AfterThrowing("pc()")
    public void exceptionLog(){
        System.out.println("异常通知：通知出现异常啦");
    }

    /**
     * 最终通知：进入finally啦---after标签
     */
    @After("pc()")
    public void finallyLog(){
        System.out.println("最终通知：进入finally啦");
    }
}
