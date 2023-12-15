package com.kxzhu.servlet; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-26 16:10
 */

import com.google.gson.Gson;
import com.kxzhu.pojo.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {

    protected void javaScriptAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("ajax请求过来了");

        //服务器返回响应:
        Person person = new Person(1,"甘雨");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);

    }

    protected void jQueryAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("jQueryAjax方法调用了");

        //服务器返回响应:
        Person person = new Person(1,"甘雨");

        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);

    }

    protected void jQueryGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("jQueryGet方法调用了");

        //服务器返回响应:
        Person person = new Person(1,"甘雨");

        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);

    }

    protected void jQueryPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("jQueryPost方法调用了");

        //服务器返回响应:
        Person person = new Person(1,"甘雨");

        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);

    }

    protected void jQueryGetJSON(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("jQueryGetJSON 方法调用了");

        //服务器返回响应:
        Person person = new Person(1,"甘雨");

        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);
    }

    protected void jQuerySerialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //浏览器发来请求:
        System.out.println("jQuerySerialize 方法调用了");
        //检查参数是否发送成功
        System.out.println("用户名：" + request.getParameter("username") );
        System.out.println("密码：" + request.getParameter("password") );
        System.out.println("single：" + request.getParameter("single") );

        //服务器返回响应:
        Person person = new Person(1,"甘雨");

        //用json字符串形式，把数据传回客户端
        Gson gson = new Gson();
        String personJsonString = gson.toJson(person);
        //用流 传回客户端
        response.getWriter().write(personJsonString);
    }

}
