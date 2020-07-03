import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/6/27
 */
public class EndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试req对象执行了doGet方法
        System.out.println("doGet is processing");
        //获取get方法的请求参数，要通过name获取
        String name = req.getParameter("name");
        System.out.println("username is "+name);

    }
}
