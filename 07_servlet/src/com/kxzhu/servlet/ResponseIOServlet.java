package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-20 16:59
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(response.getCharacterEncoding());//UTF-8

//        //设置服务器的字符集。且客户端(浏览器)和服务器的字符集要一致
//        response.setCharacterEncoding("UTF-8");
//
//        //通过响应头，设置浏览器也使用UTF-8
//        response.setHeader("Content-Type","text/html; charset=UTF-8");

        //简化（推荐）：它会同时设置服务器和客户端都使用 UTF-8 字符集，还设置了响应头
        response.setContentType("text/html; charset=UTF-8");

        //往客户端回传 字符串 数据。
        PrintWriter writer = response.getWriter();
//        writer.write("response's content.");
        writer.write("胡桃");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
