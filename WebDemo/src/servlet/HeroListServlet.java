package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Hero;
import dao.HeroDAO;

/*
*超链接listHero
* 从数据库读取对象集合，转成html字符串显示在页面
 */
public class HeroListServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");//告诉浏览器俺们的编码

        List<Hero> heros = new HeroDAO().list();//获取当前数据库对象集合
        //线程安全，与String相比StringBuffer是字符串变量
        StringBuffer sb = new StringBuffer();
        sb.append("<table align='center' border='1' cellspacing='0'>\r\n");
        //表格的表头
        sb.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>Edit</td><td>Delete</td></tr>\r\n");
        //表中数据的格式（按以下顺序塞入数据）
        // ===其中delete采用超链接的方式，用get方法向deleteHero传递要删除的id参数
        String trFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td>" +
                "<td><a href='editHero?id=%d'>介个</a></td>" +
                "<td><a href='deleteHero?id=%d'>介个</a></td></tr>\r\n";

        for (Hero hero : heros) {
            //将对象数据转换为html文本格式
            String tr = String.format(trFormat, hero.getId(), hero.getName(),
                    hero.getHp(), hero.getDamage(),hero.getId(),hero.getId());//id以get方法传递
            sb.append(tr);
        }

        sb.append("</table>");

        response.getWriter().write(sb.toString());//给出响应

    }
}