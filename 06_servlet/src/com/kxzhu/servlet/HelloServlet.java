package com.kxzhu.servlet;

//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author kxzhu
 * @create 2022-09-16 15:30
 */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("1 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 init初始化方法");

        //ServletConfig 类的三大作用:1.获取Servlet程序的别名 servlet-name的值
        System.out.println("HelloServlet程序的别名是：" + servletConfig.getServletName());
        //2、获取初始化参数 init-param
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是：" + servletConfig.getInitParameter("url"));
        //3、获取 ServletContext 对象
        System.out.println("ServletContext对象: " + servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3 service方法");
        //类型转换，因为HttpServletRequest有getMethod()方法
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        //获取是哪种请求：get还是post
        String method = httpServletRequest.getMethod();

        if ("GET".equals(method)){
            doGet();
        }else if("POST".equals(method)){
            doPost();
        }

    }

    /*
    * get请求的操作
    * */
    public void doGet(){
        System.out.println("get请求");
    }

    /*
     * post请求的操作
     * */
    public void doPost(){
        System.out.println("post请求");
    }



    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4 destroy销毁方法");
    }
}
