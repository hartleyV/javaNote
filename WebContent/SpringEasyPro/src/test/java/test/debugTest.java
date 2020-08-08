package test;

import com.spring.config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/7
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class debugTest {

    @Resource(name = "ds2")
    DataSource ds;

    @Test
    public void test(){
        try {
            //原来是没有赋值，，，，
            Connection conn = ds.getConnection();
            System.out.println(conn);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
