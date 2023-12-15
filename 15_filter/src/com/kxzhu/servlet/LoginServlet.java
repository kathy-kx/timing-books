package com.kxzhu.servlet; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-23 10:32
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //打印到页面上，需要加这句，解决响应乱码
        response.setContentType("text/html; charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if("admin".equals(username) && "admin".equals(password)){
            //登录成功
            request.getSession().setAttribute("user",username);
            response.getWriter().write("登录成功");

        } else{
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
