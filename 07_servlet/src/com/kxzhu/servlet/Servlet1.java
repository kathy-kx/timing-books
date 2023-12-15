package com.kxzhu.servlet; /**
 * @author kxzhu
 * @create 2022-09-20 14:37
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");
        System.out.println("在Servlet1(柜台1)中查看参数(材料): " + username);

        //2.给材料盖章，并传递到Servlet2查看
        //使用域数据，其他servlet也能看
        request.setAttribute("key","柜台1的章");//存在request对象中，在内存中

        //3.问Servlet2怎么走 getRequestDispatcher(要去的地方)
        /* 请求转发必须要以斜杠打头,/斜杠表示地址为:http://ip:port/工程名/,映射到IDEA代码的web目录
        */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/form.html"); //就可以访问WEB-INF中的文件了
        //走向Servlet2
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
