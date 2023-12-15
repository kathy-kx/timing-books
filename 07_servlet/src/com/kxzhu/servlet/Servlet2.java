package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-20 14:40
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 形参request，是Servlet1的【requestDispatcher.forward(request,response)】传过来的request，
    // 所以Servlet1和Servlet2的域数据一样（都存在request对象中）
        //1.获取请求的参数
        String username = request.getParameter("username");
        System.out.println("在Servlet2(柜台2)中查看参数(材料): " + username);

        //2.查看Servlet1的章(两边key一致)
        Object key = request.getAttribute("key");
        System.out.println("柜台1是否有章：" + key);

        //3.处理Servlet2自己的业务
        System.out.println("Servlet2处理自己的业务");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
