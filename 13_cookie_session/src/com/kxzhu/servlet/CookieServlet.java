package com.kxzhu.servlet; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-13 15:52
 */

import com.kxzhu.util.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CookieServlet extends BaseServlet {
    //报405，找不到get方法：注意 继承的是BaseServlet！！ 不是HttpServlet

    /**
     * 创建Cookie
     */
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建cookie对象
        Cookie cookie = new Cookie("key1","value1");

        //2.通知客户端保存Cookie
        //服务器发给客户端的，都是通过响应response来操作
        response.addCookie(cookie);//会在response headers响应头中添加一行：Set-Cookie: key1=value1
        response.getWriter().write("Cookie创建成功");//输出给客户端
    }

    /**
     * 服务器获取浏览器传来的Cookie
     */
    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器获取浏览器传来的cookie：
        Cookie[] cookies = request.getCookies();
        //查找所有的cookie
        for (Cookie cookie: cookies){
            // getName()方法 返回Cookie的key(名称)
            // getValue()方法 返回Cookie的value(值)
            //换行：浏览器只认<br/>
            response.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "]<br/>");//输出给客户端
        }
        //查找指定的cookie
        Cookie cookieIWant = CookieUtils.findCookie("key1",cookies);//在cookies中查找键为key1的指定cookie
        if(cookieIWant != null){
            response.getWriter().write("找到了需要的cookie");
        }
    }

    /**
     * Cookie值的修改
     */
    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //方案一：
        //1、先创建一个与要修改的cookie同名(同key)的Cookie对象
        //2、在构造器，同时赋于新的 Cookie 值
        //Cookie cookie = new Cookie("key1","value1New");
        ////3、调用 response.addCookie( Cookie );
        //response.addCookie(cookie);

        //方案二：
        //1、先查找到需要修改的 Cookie 对象
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.findCookie("key1", cookies);
        //赋值前先判断！因为有可能找不到：
        if (cookie != null){
            //2、调用setValue()方法 赋于新的 Cookie 值。
            cookie.setValue("value1NewNew");
            //3、调用response.addCookie()通知客户端保存修改
            response.addCookie(cookie);
        }

        response.getWriter().write("key1的Cookie已修改");
    }

    /**
     * Cookie的存活时间设置
     */
    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife","defaultLife");
        cookie.setMaxAge(-1);//设置存活时间
        response.addCookie(cookie);
    }

    /**
     * 立即删除Cookie
     */
    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先找到你要删除的cookie对象
        Cookie cookie = CookieUtils.findCookie("key1", request.getCookies());
        if(cookie != null){
            // 调用 setMaxAge(0);
            cookie.setMaxAge(0);// 表示马上删除，都不需要等待浏览器关闭
            // 调用 response.addCookie(cookie);
            response.addCookie(cookie);

            response.getWriter().write("key1的Cookie已删除");
        }
    }

    /**
     * Cookie存活一段时间
     */
    protected void live3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("live3600","live3600");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        response.getWriter().write("已经创建了一个存活1h的cookie。");
    }

    /**
     * Cookie的路径过滤
     */
    protected void testPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1","path1");
        //getContextPath():得到工程路径
        cookie.setPath(request.getContextPath() + "/abc");//--> /工程路径/abc
        response.addCookie(cookie);
        response.getWriter().write("创建了一个带有Path路径的cookie");
    }


    }
