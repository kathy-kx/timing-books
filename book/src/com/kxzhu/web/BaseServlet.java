package com.kxzhu.web; /**
 为了代码复用，被别的servlet继承
 做的事：
 获取jsp页面发来的隐藏域中，name="action"对应的value，即:action是login/regist/...？
 action是什么，就调用相应的方法。方法不在本Servlet中提供，而是别的servlet继承BaseServlet时体现。
 * @author kxzhu
 * @create 2022-10-03 14:39
 */

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        //login.jsp和regist.jsp中，用隐藏域，向服务器发来了方法名，即<input type="hidden" name="action" value="login"/>
        //Tomcat服务器翻译jsp页面后，就有了内置对象request，Tomcat服务器会把HTTP协议解析封装在request中，
        // 则request存了键值对：键name="action" 值value="login"
        //服务器程序中，用request.getParameter("action")，可获得本次请求的方法名（值）"login"

        //利用反射优化代码：
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);//获取action字符串相应的方法
            //继承方法谁调用，this是谁，所以反射也是子类UserServlet的

            //method = UserServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
            //method.invoke(new UserServlet(),request,response);
            //我暂时的理解：
            //反射也可以用 类名.class和new 构造器，UserServlet.class和new UserServlet()此处也可以正常运行
            //但是此处用this，则继承此类的子类UserServlet对象可以直接使用以上这三个语句。

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); //把异常抛给filter过滤器
        }

//代替以下if-else语句
        //if ("login".equals(action)){
        //    //处理登录的需求
        //    login(request,response);
        //
        //}else if("regist".equals(action)){
        //    //处理注册的需求
        //    regist(request,response);
        //}

    }
}
