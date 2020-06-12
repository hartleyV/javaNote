package servlet;

import bean.Hero;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/6/3
 */
public class SubmitServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("data");
        System.out.println("service接收的数据为"+data);

        JSONObject json = JSONObject.fromObject(data);
        System.out.println("转换为Json的数据为"+json);

        Hero hero = (Hero) JSONObject.toBean(json, Hero.class);
        System.out.println("json出来的hero对象："+hero);

    }
}
