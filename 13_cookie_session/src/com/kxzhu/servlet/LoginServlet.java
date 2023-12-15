package com.kxzhu.servlet; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-14 14:53
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if("admin".equals(username) && "admin".equals(password)){
            //登录成功
            //把用户名保存为cookie，发送给浏览器客户端
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60 * 60 * 24 * 7);//如果不设置时间，退出浏览器的时候就没有该cookie了。此处是1星期有效
            response.addCookie(cookie);
            System.out.println("登录成功");
        }else {
            //登录失败
            System.out.println("登录失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
