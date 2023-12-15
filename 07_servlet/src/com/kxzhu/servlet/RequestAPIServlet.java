package com.kxzhu.servlet;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示HttpServletRequest类的常用方法
 * @author kxzhu
 * @create 2022-09-20 11:04
 */
public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //getRequestURI() 获取请求的资源路径
        System.out.println("URI => " + req.getRequestURI());//URI => /07_servlet/requestAPIServlet

//        getRequestURL() 获取请求的统一资源定位符(绝对路径)
        System.out.println("URL => " + req.getRequestURL());//URL => http://localhost:8080/07_servlet/requestAPIServlet

        //getRemoteHost() 获取客户端的ip地址
        System.out.println("客户端ip地址 => " + req.getRemoteHost());//0:0:0:0:0:0:0:1   ???

        //getHeader() 获取请求头
        System.out.println("请求头User-Agent ==>> " + req.getHeader("User-Agent"));//Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36

        //getMethod() 请求的方式
        System.out.println("请求的方式==>> " + req.getMethod());
    }


}
