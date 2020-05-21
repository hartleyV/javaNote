package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ProductService;

/**
 * ​AOP(Aspect Oriented Program)面向切面编程
    核心业务功能和切面功能分别独立进行开发，然后选择性的，低耦合的把切面和核心业务功能结合
    在一起的编程思想，就叫做切面编程
 *涉及类：service包下的核心业务类 + aspect包下的切面类
 *【1】配置xml
 *【2】利用注解
 * @author Hartley
 * @create 2020/5/21
 */
public class AOPTest {

    @Test
    public void test(){
        //【1】通过配置文件，在核心功能执行的同时，执行附加功能~
        String xml1 = "context4.xml";
        //【2】通过注解，
        String xml2 = "context5.xml";

        String xml = xml2;

        ApplicationContext context = new
                ClassPathXmlApplicationContext(new String[] { xml });
        ProductService s = (ProductService) context.getBean("s");
        s.doSomeService();
    }
}
