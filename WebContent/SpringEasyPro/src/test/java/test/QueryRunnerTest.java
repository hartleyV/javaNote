package test;

import com.spring.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * description：
测试QueryRunner是否为单例
 * @author Hartley
 * date： 2020/8/4
 */
public class QueryRunnerTest {

    @Test
    public void test(){
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner runner1 = (QueryRunner) ac.getBean("runner");
        QueryRunner runner2 = (QueryRunner) ac.getBean("runner");

        System.out.println(runner1 == runner2);
    }
}
