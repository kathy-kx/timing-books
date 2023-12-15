package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-20 11:26
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---------doGet--------");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //单选的情况
//        String hobby = request.getParameter("hobby");
//        System.out.println("用户名：" + username + "\n密码：" + password + "\n兴趣爱好：" + hobby);
        //多选的情况
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("用户名：" + username + "    密码：" + password + " 兴趣爱好：" + Arrays.asList(hobbies));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //// 设置请求体的字符集为 UTF-8，从而解决 post 请求的中文乱码问题
        //要在获取请求参数之前调用，才有效。放到最前面
        request.setCharacterEncoding("UTF-8");

        System.out.println("---------doPost--------");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //单选的情况
//        String hobby = request.getParameter("hobby");
//        System.out.println("用户名：" + username + "\n密码：" + password + "\n兴趣爱好：" + hobby);
        //多选的情况
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("用户名：" + username + "\n密码：" + password + "\n兴趣爱好：" + Arrays.asList(hobbies));

    }
}
