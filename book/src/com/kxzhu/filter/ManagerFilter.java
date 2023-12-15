package com.kxzhu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName ManagerFilter
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-23 16:41
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object user = httpServletRequest.getSession().getAttribute("user");

        if(user == null){
            //没登录
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        } else {
            //已登录，则放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
