package com.kxzhu.web; /**
 * @author kxzhu
 * @create 2022-09-24 20:24
 */

import com.kxzhu.pojo.User;
import com.kxzhu.service.UserService;
import com.kxzhu.service.impl.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用userService的方法处理业务（login登录）
        User loginUser = userService.login(new User(null, username, password, null));
        if(loginUser == null){
            //返回null表示login失败，则跳至登录页
            //把错误信息和回显的表单信息，保存到request域中
            request.setAttribute("errMsg","用户名或密码错误");
            request.setAttribute("username",username);
            //跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //返回用户表示login成功，则跳至成功页
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }
}
