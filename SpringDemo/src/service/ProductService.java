package service;

import org.springframework.stereotype.Component;

/**
 * AOP面向切面编程
 * 核心业务类
 * @author Hartley
 * @create 2020/5/21
 */
@Component("s")//标记该类为bean，受Spring控制，并有名字s
public class ProductService {
    public void doSomeService(){

        System.out.println("doSomeService");

    }
}
