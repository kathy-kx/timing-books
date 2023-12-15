package com.kxzhu.filter;

import com.kxzhu.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * @ClassName TransactionFilter——事务的Filter
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-24 20:42
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给tomcat管理，展示友好的页面
        }
    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
