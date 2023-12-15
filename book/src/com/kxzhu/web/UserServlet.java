package com.kxzhu.web;
/**
 * 使用UserServlet代替LoginServlet & RegistServlet
 * 用户注册和登录
 * @author kxzhu
 * @create 2022-10-02 23:30
 */

import com.google.gson.Gson;
import com.kxzhu.pojo.User;
import com.kxzhu.service.UserService;
import com.kxzhu.service.impl.UserServiceImpl;
import com.kxzhu.test.UserServletTest;
import com.kxzhu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();//为检查用户名是否正确，需要调用service，处理业务。造一个xxxService的对象


    /**
     * 处理注册的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取验证码：
        //获取session中的验证码，存到token中
        String token = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);//这里（token）是服务器生成的验证码
        //立即删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求的参数
        String username = request.getParameter("username");//参数是表单项<input> 的name属性 的值
        String password = request.getParameter("password");
        //注册阶段的确认密码？？
        String email = request.getParameter("email");
        String code = request.getParameter("code");//这里（code）是用户提交表单输入的验证码

        //原本是：
        //User user = new User();
        //WebUtils.copyParamToBean(request.getParameterMap(),user);
        //优化后：
        User user = WebUtils.copyParamToBean(request.getParameterMap(),new User());
        //把request中参数的Map形式，注入到User的对象中

        //2.检查验证码是否正确  暂时写死检查，要求验证码是abcde
        if (token!=null && token.equalsIgnoreCase(code)){
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

    //这里不是继承了BaseServlet，doPost里面通过this进行反射，
    //这就很妙，因为继承方法，谁调this是谁，所以反射也是子类里面的

    /**
     * 处理登录的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
            //返回用户表示login成功
            //存到session域；之后从sessionScope取
            request.getSession().setAttribute("loginUserName",loginUser.getUsername());
            request.getSession().setAttribute("user",loginUser);
            // 则跳至成功页
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

        //老师的这句：req.getSession().setAttribute("user", loginUser);有问题哦，因为Cookie中不能保存一些特殊字符
        //这样写，后登录成功之后，会跳转到一个空白页面，因为报错了
        //可以在Session域中，只保存姓名：req.getSession().setAttribute("loginUserName", loginUser.getUserName());


    }

    /**
     * 注销（退出登录）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、销毁session中的用户登录信息，或者清除session
        request.getSession().invalidate();//invalidate()：让当前session马上超时无效

        //2、重定向到首页，或者登录页
        response.sendRedirect(request.getContextPath());//到首页：request.getContextPath()即可

    }

    /**
     * 验证用户名是否可用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数username
        String username = request.getParameter("username");

        //调用userService的existsUsername()
        boolean existsUsername = userService.existsUsername(username);

        //把返回的结果封装成为 map 对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        //转成json字符串
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        //通过响应的字符输出流，把json对象输出到客户端
        response.getWriter().write(json);//json对象中有一个键为existsUsername，值为boolean型数据（username是否存在）
    }



}
