import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sevlet练习
 * 执行顺序；
      调用servlet的空参构造器，
      执行从HttpServlet继承的init(ServletConfig config)（只执行一次）
      执行service方法
 *中文乱码问题
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
 * 【request】常用方法（HttpServletRequest ）
         getRequestURL(): 浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有)"
         getRequestURI(): 浏览器发出请求的资源名部分，去掉了协议和主机名"
         getQueryString(): 请求行中的参数部分，只能显示以get方式发出的参数，post方式的看不到
         getRemoteAddr(): 浏览器所处于的客户机Remote的IP地址
         getRemoteHost(): 浏览器所处于的客户机的主机名
         getRemotePort(): 浏览器所处于的客户机使用的网络端口
         getLocalAddr(): 服务器的IP地址
         getLocalName(): 服务器的主机名
         getMethod(): 得到客户机请求方式一般是GET或者POST

         getParameter(): 是常见的方法，用于获取单值的参数
         getParameterValues(): 用于获取具有多值的参数，比如注册时候提交的 "hobits"，可以是多选的。
         getParameterMap(): 用于遍历所有的参数，并返回Map类型。

         getHeader() 获取浏览器传递过来的头信息。比如getHeader("user-agent") 可以获取是什么浏览器
         getHeaderNames() 获取浏览器所有的头信息名称，根据头信息名称就能遍历出所有的头信息

         setAttribute和getAttribute可以用来在进行服务端跳转的时候，在不同的Servlet之间进行数据共享

 * 【response】常用方法（HttpServletResponse）
    PrintWriter pw= response.getWriter();
        pw的println(),append(),write(),format()等等方法设置返回给浏览器的html内容
        setContentType("text/lol");把响应内容类型设置成浏览器不能解析，会激活下载操作

        setCharacterEncoding("UTF-8");仅对响应内容指定编码
        setContentType("text/html; charset=UTF-8");此外，该方法还会通知浏览器用哪个编码

        setStatus(301);301永久跳转 response.setHeader("Location", "fail.html");
        sendRedirect("fail.html");302临时跳转

        setDateHeader("Expires", 0);通知浏览器不要使用缓存
        setHeader("Cache-Control", "no-cache");
        setHeader("pragma", "no-cache");
 */
public class LoginServlet extends HttpServlet {

    //（1）首先会通过空参构造方法创建servlet对象======================================
    public LoginServlet(){
        System.out.println("调用了空参构造器");
    }

    //（2）然后执行init方法，===========================================
    //在web.xml中设定该servlet的load-on-startup参数，将自动执行该方法
    public void init(ServletConfig config){
        System.out.println("init方法执行ing");
    }

    //（3）再者是service方法=======================================================
    /*
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

    }*/

    //（4）再在者post、get方法=======================================================
    //html页面的form表单是通过post传输的，所以servlet也需要相应的doPost方法来获取输入参数
    //tomcat服务器会根据html页面的post自动调用对应servlet中的doPost方法【2'】
    //html通过web.xml与servlet相互对应
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设定解码为utf-8，解决读入数据中 中文乱码问题
        request.setCharacterEncoding("UTF-8");

        //通过request获取表格参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //String html = null;

        //在内存中校验输入的账号密码
        if("admin".equals(name)&&"123".equals(password)){

            //【服务端跳转】（路径不变，仍为/login
            //服务端读取success页面，并将其内容发给客户端
            request.getRequestDispatcher("success.html").forward(request, response);
            //html = "<div style='color:green'>login successfully，稀饭你</div>";
        }else{

            //【客户端端跳转】（路径改变为/fail.html
            //发送信息给客户端，让其访问fail.html页面，客户端请求后，服务端再把succ页面读出，发送内容302
            //response.sendRedirect("fail.html");
            //301永久跳转
            response.setStatus(302);
            response.setHeader("Location", "fail.html");

            //html = "<div style='color:red'>no!!!!!再瞅瞅</div>";
        }
        //设定响应的html为utf-8编码，解决中文乱码问题
        response.setContentType("text/html; charset=UTF-8");

        //通过response反馈给html页面信息
        //response.getWriter().println(html);

        System.out.println("name:" + name);
        System.out.println("password:" + password);

        /*
        request方法
         */
        System.out.println("==============request方法===============");
        System.out.println("浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + request.getRequestURL());
        System.out.println("浏览器发出请求的资源名部分，去掉了协议和主机名: " + request.getRequestURI());
        System.out.println("请求行中的参数部分: " + request.getQueryString());
        System.out.println("浏览器所处于的客户机的IP地址: " + request.getRemoteAddr());
        System.out.println("浏览器所处于的客户机的主机名: " + request.getRemoteHost());
        System.out.println("浏览器所处于的客户机使用的网络端口: " + request.getRemotePort());
        System.out.println("服务器的IP地址: " + request.getLocalAddr());
        System.out.println("服务器的主机名: " + request.getLocalName());
        System.out.println("得到客户机请求方式: " + request.getMethod());
        System.out.println("==========================================");
    }

    //get
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("请求行中的参数部分: " + request.getQueryString());

        //设置编码
        response.setContentType("text/html; charset=UTF-8");
        //写入html页面
        PrintWriter pw = response.getWriter();
        pw.println("<h1>介是LoginServlet的doGet方法</h1>");
        //把响应内容类型设置成浏览器不能解析，会激活下载操作
        response.setContentType("text/lol123564");
    }
}