package servlet;

/**
 * description：通过js发送响应数据
 *
 * @author Hartley
 * date： 2020/6/4
 */
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import net.sf.json.JSONObject;

public class GetOneServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //构建对象
        Hero hero = new Hero();
        hero.setName("盖伦");
        hero.setHp(353);
        //创建JSONObject对象
        JSONObject json= new JSONObject();
        //将字符串hero作为json的key
        json.put("hero", JSONObject.fromObject(hero));
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        //将设置好的json响应出去
        response.getWriter().print(json);
    }
}
