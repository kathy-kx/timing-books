package com.kxzhu.web; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-06 10:20
 */

import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;
import com.kxzhu.service.BookService;
import com.kxzhu.service.impl.BookServiceImpl;
import com.kxzhu.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 负责添加图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求表单的参数，并封装为Bean（Book对象）
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());

        //2、调用bookService的addBook()，向数据库添加book
        bookService.addBook(book);

        //3、跳转到图书列表页面，显示添加后的图书列表
        //request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);
        //bug：
        // 如果请求转发的过程中，网卡顿，用户点了刷新，就会重复提交，造成bug：
        //解决：
        // 请求转发是一次请求，造成表单重复提交的bug；要使用请求重定向，是两次请求。
        //原理：
        // 重定向时：add图书之后，通知浏览器重定向，访问/manager/bookServlet?action=list，浏览器地址变了，
        // 此时用户刷新，只会执行list()，不会再add()；
        // 请求转发时：第一次访问的是manager/bookServlet，其中action=add，
        // 请求转发时浏览器地址不变，已经add过了，也跳到list()了，但地址栏不会跳转到/manager/bookServlet?action=list，
        // 此时用户在图书管理页面（列表页面）又刷新了，又会访问manager/bookServlet，其中action=add，又添加一次，出现bug
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo+=1;//pageNo越界，直接显示最后一页//有bug
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
        //getContextPath() 获取当前工程路径
        //sendRedirect()中的地址是浏览器解析，/斜杠代表到端口地址，所以不对，需要加上工程路径。
        // 可是工程地址前面为什么不再加/呢？如果加了/，就成了http://book/manager/bookServlet?action=list。为什么？

    }


    /**
     * 负责删除图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数
        int id = WebUtils.parseInt(request.getParameter("id"), 0);//为什么id获取来的是string型？键值对存的都是string？

        //2、调用bookService的deleteBookById()，从数据库中删除该book
        bookService.deleteBookById(id);

        //3、（与数据库有交互，仍是重定向）跳转至图书管理列表页面/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    /**
     * 负责在修改图书时，从数据库获取并显示某一图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数，即图书id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        //2、通过调用bookService的queryBookById()，从数据库中查询该book信息
        Book book = bookService.queryBookById(id);

        //3、由于要在浏览器端显示，所以先存到request域中
        request.setAttribute("book", book);

        //4、请求转发到修改图书页面book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

    }


    /**
     * 负责修改图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数，并封装为bean对象（book对象）
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());

        //2、调用bookService的updateBook(book)方法，修改图书
        bookService.updateBook(book);

        //3、重定向到图书列表页面/manager/bookServlet?action=list，并显示更新的全部图书
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * 负责查询所有图书信息
     * 需要更新图书时（添加、删除、修改）需要调用一次list，从数据库中查询数据，
     * 再请求转发到book_manager页面，来显示全部图书数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用bookService查询全部图书信息
        List<Book> books = bookService.queryBooks();

        //2、把图书信息保存至request域（才能被浏览器获取）
        request.setAttribute("books",books);//setAttribute()的"值"可以是List型

        //3、请求转发，到/pages/manager/book_manager.jsp
        //请求转发算是一次请求，浏览器地址栏不会变，仍为/manager/bookServlet?action=list
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 处理分页功能
     * 查询图书信息并显示所需要的分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从浏览器端获取请求的参数pageNo, pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2、调用service处理业务，返回有属性的page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置后台的请求地址url
        page.setUrl("manager/bookServlet?action=page");//这是后台管理-图书管理的url

        //3、保存page对象至request域
        request.setAttribute("page",page);

        //4、请求转发至/pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

        //报NumberFormatException:是因为在page方法中获取pageSize为空，我们在前端没有发送pageSize，所以采用的是默认值4
    }

}
