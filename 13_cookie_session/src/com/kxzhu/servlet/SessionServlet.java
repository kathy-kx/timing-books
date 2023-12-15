package com.kxzhu.servlet; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-14 15:51
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionServlet extends BaseServlet {

    protected void createOrGetSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建或获取session会话对象
        //第一次调用是创建；之后调用是获取
        HttpSession session = request.getSession();
        //判断当前session会话 是不是刚创建出来的
        boolean isNew = session.isNew();
        //获取Session会话的唯一标识 id
        String id = session.getId();

        response.getWriter().write("得到的session，id是：" + id + "<br/>");
        response.getWriter().write("这个session是否是新创建的：" + isNew + "<br/>");
    }

    /**
     * 往session中保存数据
     */
    protected void setAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("key1","value1");
        response.getWriter().write("已经往session中保存了数据");
    }

    /**
     * 从session域中获取数据
     */
    protected void getAttribute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute("key1");//存和取的key要相同
        response.getWriter().write("从session中取出key1的值是：" + attribute);
    }

    /**
     * session的默认超时时长
     */
    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session的默认超时时长
        int interval = request.getSession().getMaxInactiveInterval();
        response.getWriter().write("session的超时时长为：" + interval + "秒");

    }

    /**
     * 修改个别 Session 的超时时长
     */
    protected void live3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setMaxInactiveInterval(3);
        response.getWriter().write("已经设置为3秒后超时");
    }

    /**
     * 让session马上超时销毁
     */
    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.getWriter().write("session会话已经设置为超时（无效）");
    }


    }
