package com.kxzhu.web; /**
 前台
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-11 21:35
 */

import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;
import com.kxzhu.service.BookService;
import com.kxzhu.service.impl.BookServiceImpl;
import com.kxzhu.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("经过了前台的ClientBookServlet程序");
        //1、从浏览器端获取请求的参数pageNo, pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2、调用service处理业务
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置后台的请求地址url
        page.setUrl("client/bookServlet?action=page");

        //3、保存page对象至request域
        request.setAttribute("page",page);

        //4、请求转发至/pages/client/index.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    /**
     * 处理价格搜索功能
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、从浏览器端获取请求的参数pageNo, pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);


        //2、调用service处理业务
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);


        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");//方法内部的变量不需要线程安全的StringBuffer
        //如果参数中有min，即最小价格这一参数，则追加到分页条的请求地址中
        if (request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min")) ;
        }
        //如果参数中有max，即最大价格这一参数，则追加到分页条的请求地址中
        if (request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max")) ;
        }

        //设置后台的请求地址url
        page.setUrl(sb.toString());

        //3、保存page对象至request域
        request.setAttribute("page",page);

        //4、请求转发至/pages/client/index.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
