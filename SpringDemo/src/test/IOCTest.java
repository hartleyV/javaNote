package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Category;
import pojo.Product;

/**
 * IOC​(Inversion of Control)控制反转
 通知Spring创建对象，控制权在Spring中，并且可以通过Spring进行成员变量（属性、对象）注入
 *涉及类：pojo包下的
 *【1】通过xml添加Bean语句
 *【2】对于对象成员的注入，可在声明对象成员前加注解@Autowired（不需要xml配置ref了）
 *【3】在待注入的Bean类前用@Component("Bean的名字")，在xml中仅需要声明寻找包的语句，
      从而可以把Category,Product移出xml配置文件
 * @author Hartley
 * @create 2020/5/21
 */
public class IOCTest {
    @Test
    public void test(){
        //【1】通过xml注入属性，创建对象
        String xml1 = "context1.xml";
        //【2】在声明对象成员前加注解@Autowired（不需要xml配置了）或者在setXXX方法前声明
        String xml2 = "context2.xml";
        //【3】通过注解声明Bean类，在xml中只需配置Bean类在哪个包中
        String xml3 = "context3.xml";

        String xml = xml3;


        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { xml });//读取xml配置

        Product p = (Product)context.getBean("p");//通过Spring获取对象

        System.out.println(p.getName());
        System.out.println(p.getCategory().getName());//获取对象成员的属性
    }

}
