package com.kxzhu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName Filter2
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-23 13:00
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);//报错，暂时注释掉
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter2的前置代码");

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("Filter2的后置代码");
    }

    @Override
    public void destroy() {
        System.out.println("Filter的destroy()");
    }
}
