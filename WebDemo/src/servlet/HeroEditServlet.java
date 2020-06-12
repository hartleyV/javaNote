package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description：
 * 超链接listHero的编辑功能
    通过传入的id获取Hero对象，写出一个带值的html页面，提交后交给路径、undateHero
 * @author Hartley
 * date： 2020/5/30
 */
public class HeroEditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Hero hero = new HeroDAO().get(id);

        //开始用Printwriter用字符串写页面,
        StringBuffer format = new StringBuffer();
        //声明编码
        resp.setContentType("text/html; charset=UTF-8");

        format.append("<!DOCTYPE html>" );

        //表格提交后以post方式传给路径updatHero
        format.append("<form action='updateHero' method='post'>");
        format.append("名字 ： <input type='text' name='name' value='%s' > <br>");
        format.append("血量 ： <input type='text' name='hp'  value='%f' > <br>");
        format.append("伤害： <input type='text' name='damage'  value='%d' > <br>");
        format.append("<input type='hidden' name='id' value='%d'>");
        format.append("<input type='submit' value='更新'>");
        format.append("</form>");

        //通过获取的id对应hero对象，向表格填值
        String html = String.format(format.toString(),hero.getName(),hero.getHp(),
                hero.getDamage(),hero.getId());

        resp.getWriter().write(html);


    }

}
