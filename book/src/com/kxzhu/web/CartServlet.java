package com.kxzhu.web; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-17 22:16
 */

import com.google.gson.Gson;
import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Cart;
import com.kxzhu.pojo.CartItem;
import com.kxzhu.service.BookService;
import com.kxzhu.service.impl.BookServiceImpl;
import com.kxzhu.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("请求头referer的值" + request.getHeader("Referer"));//可以得到 谁发来请求 的地址
                //需要拿到id，再使用Cart的addItem（）
        //System.out.println("加入购物车");
        //System.out.println("商品编号=" + request.getParameter("id"));

        //1、获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        //2、调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);

        //3、把图书信息（book），转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //why totalPrice这里是book.getPrice()：因为是1件商品

        //4、调用 cart.addItem(CartItem);添加商品项
        //Cart cart = new Cart();不能每次加进一个东西，就换一个购物车。购物车应该只有一辆
        Cart cart = (Cart) request.getSession().getAttribute("cart");//本版本把cart放在session中，从session中获取购物车
        if (cart == null){
            //如果购物车还没有，就创建一个，放到session中
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //System.out.println(cart);

        //最后一个添加的商品名称，存到session中
        request.getSession().setAttribute("lastName",cartItem.getName());

        //5、重定向回 原来商品所在的地址页面(jsp文件发来请求时，请求头中会有referer。可以记录 发来请求时 浏览器的地址)
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * 删除购物车内的商品项
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 获取购物车对象（才能调用Cart类的deleteItem方法）
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart != null){
            // 删除了购物车商品项
            cart.deleteItem(id);
            // 删除之后，从哪来，回哪去 ————重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));//从哪来回哪去
        }
    }

    /**
     * 清空购物车内的所有商品项
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车商品数量
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if(cart != null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向回原来购物车展示页面(刷新页面)
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 使用ajax请求，加入购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数 商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        //2、调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);

        //3、把图书信息（book），转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //why totalPrice这里是book.getPrice()：因为是1件商品

        //4、调用 cart.addItem(CartItem);添加商品项
        //Cart cart = new Cart();不能每次加进一个东西，就换一个购物车。购物车应该只有一辆
        Cart cart = (Cart) request.getSession().getAttribute("cart");//本版本把cart放在session中，从session中获取购物车
        if (cart == null){
            //如果购物车还没有，就创建一个，放到session中
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        //5、调用cart.addItem()添加商品项
        cart.addItem(cartItem);

        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName",cartItem.getName());

        //6、客户端需要什么，就返回什么
        //返回购物车总商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);//把resultMap转为json字符串
        response.getWriter().write(resultMapJsonString);//把json字符串写入到客户端
    }
}
