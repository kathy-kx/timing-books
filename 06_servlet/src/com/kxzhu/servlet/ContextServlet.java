package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-19 14:47
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletContext类的四个作用
        // 1、获取 web.xml 中配置的上下文参数 context-param
        ServletContext servletContext = getServletConfig().getServletContext();//getServletConfig()是父类的方法，子类可以直接调用，这个方法返回一个对象
        String username = servletContext.getInitParameter("username");//传参数名 获取参数值
        System.out.println("context-param参数username的值是：" + username);

        System.out.println("context-param参数password的值是：" + servletContext.getInitParameter("password"));
        // 2、获取当前的工程路径，格式: /工程路径
        System.out.println("当前工程路径：" + servletContext.getContextPath());

        // 3、获取工程部署后,在服务器硬盘上的绝对路径
        System.out.println("工程部署的绝对路径是：" + servletContext.getRealPath("/"));
        // /Users/xiwang/Desktop/workspace_idea/JavaWeb/out/artifacts/06_servlet_war_exploded/ 即该工程在服务器硬盘上的绝对路径
        // 其对应着IDEA中，模块名06_servlet/web目录。即/就已经到了web目录
        // 注：/ 斜杠被服务器解析地址为:http://ip:port/工程名/
        System.out.println("工程下css目录的绝对路径是：" + servletContext.getRealPath("/css"));
        System.out.println("工程下imgs目录下26.jpg的绝对路径是：" + servletContext.getRealPath("/imgs/26.jpeg"));

        // 4、像Map一样存取数据
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
