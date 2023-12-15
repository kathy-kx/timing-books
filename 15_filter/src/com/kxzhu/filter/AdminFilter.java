package com.kxzhu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName AdminFilter
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-21 11:26
 */
public class AdminFilter implements Filter {
    public AdminFilter() {
        System.out.println("1、Filter的构造器方法");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2、Filter的init()初始化");
    }

    /**
     * 专门用于【拦截请求】、过滤响应
     * 例如，登录后才可以访问某页面
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter的doFilter()");

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpServletRequest.getSession();

        Object user = session.getAttribute("user");

        if(user == null){
            //没有登录
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            return;
        }else{
            //登录了，放行：用户要访问啥，就让他去访问
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Filter的destroy()");
    }
}
