import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * servlet中
    request【1】获取参数的方法
        单值
        多值
        所有参数的map集合
    【2】获取头信息
         request.getHeader() 获取浏览器传递过来的头信息。
         request.getHeaderNames() 获取浏览器所有的头信息名称，根据头信息名称就能遍历出所有的头信息
 */
public class RegisterServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //=========================【1】=========================
        System.out.println("获取单值参数name:" + request.getParameter("name"));

        String[] hobits = request.getParameterValues("hobits");
        System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));

        System.out.println("通过 getParameterMap 遍历所有的参数： ");
        Map<String, String[]> parameters = request.getParameterMap();

        Set<String> paramNames = parameters.keySet();
        for (String param : paramNames) {
            String[] value = parameters.get(param);
            System.out.println(param + ":" + Arrays.asList(value));
        }

        //=========================【2】=========================
        String exploreType = request.getHeader("user-agent");
        System.out.println("宁的浏览器为："+exploreType);

        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String header = headers.nextElement();
            String value = request.getHeader(header);
            System.out.println("表头为"+header+":对应参数为"+value);
        }

    }

}