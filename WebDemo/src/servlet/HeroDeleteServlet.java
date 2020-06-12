package servlet;

import dao.HeroDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description：
 *超链接listHero的删除功能
 * @author Hartley
 * date： 2020/5/30
 */
public class HeroDeleteServlet extends HttpServlet {
    //通过get方法，或者service方法响应
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取id，用dao删除对应数据
        int id = Integer.parseInt(request.getParameter("id"));//HeroListServlet传过来的
        new HeroDAO().delete(id);
        response.sendRedirect("/listHero");

    }
}
