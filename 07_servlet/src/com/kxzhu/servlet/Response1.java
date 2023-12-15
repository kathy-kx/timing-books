package com.kxzhu.servlet; /**
 * 演示：请求重定向
 * @author kxzhu
 * @create 2022-09-20 18:57
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("来过Response1");

        //设置响应码和响应头
        //所有设置给客户端的都由response对象来做
//        response.setStatus(302);//响应码302表示重定向
//        response.setHeader("Location","http://localhost:8080/07_servlet/response2");//响应头告知新地址

        //第二种方法（推荐）
        response.sendRedirect("http://localhost:8080/07_servlet/response2");//只需要告知地址即可
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
