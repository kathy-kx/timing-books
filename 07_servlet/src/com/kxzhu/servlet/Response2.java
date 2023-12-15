package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-20 18:58
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().write("response2's 结果");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        对于转发和重定向的req和resp，
//        想象他俩就是两本书，不管转发多少次，都一直在服务器内，人人传阅和添加内容，
//        但是对于重定向，req和resp在一次重定向之后就被处理掉了
    }
}
