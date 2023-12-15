package com.kxzhu.web; /**
 * @author kxzhu
 * @create 2022-09-22 22:34
 */

import com.kxzhu.pojo.User;
import com.kxzhu.service.UserService;
import com.kxzhu.service.impl.UserServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();//为检查用户名是否正确，需要调用service

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //密码不希望被看见，所以使用doPost()
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");//参数是表单项<input> 的name属性 的值
        String password = request.getParameter("password");
        //注册阶段的确认密码？？
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //2.检查验证码是否正确  暂时写死检查，要求验证码是abcde
        if ("abcde".equalsIgnoreCase(code)){
            //验证码正确
            //3.检查用户名是否可用
            if(userService.existsUsername(username)){
                //注册失败的情况：用户名不可用，跳回注册页，并回显错误信息和已填信息
                //把回显信息保存到request域：
                request.setAttribute("errMsg","用户名已存在");
                //并且回显用户名和邮箱：
                request.setAttribute("username",username);
                request.setAttribute("email",email);

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            } else {
                //可用：调用service，保存到数据库；并跳转至注册成功页
                userService.registUser(new User(null,username,password,email));//将参数信息封装成Bean对象，调用xxxService的方法registUser()
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);

            }

        }else {
            //注册失败的情况：验证码错误，跳回注册页，并回显错误信息和已填信息

            //把回显信息保存到request域：
            request.setAttribute("errMsg","验证码错误");
            //并且回显用户名和邮箱：
            request.setAttribute("username",username);
            request.setAttribute("email",email);

            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
        /*
        如果信息只是带去给服务器，则使用Bean对象封装，再调用Service的方法处理业务；
        如果信息带去给服务器，还需要【回传】回来，即服务器至浏览器；
        则需要在服务器Servlet（发送端）使用request域的setAttribute()存数据，
        在客户端jsp文件（接收端）使用getAttribute()接收数据
        */

    }
}
