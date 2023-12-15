package com.kxzhu.web; /**
 * @ClassName ${NAME}
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 22:09
 */

import com.kxzhu.pojo.Cart;
import com.kxzhu.pojo.User;
import com.kxzhu.service.OrderService;
import com.kxzhu.service.impl.OrderServiceImpl;
import com.kxzhu.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //需要先有cart和userId
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //获取userId
        User loginUser = (User) request.getSession().getAttribute("user");

        //如果用户没登录，就转去登录
        if (loginUser == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return; //转发和重定向后，一般不需要再执行任何代码了，加个return
        }
        Integer userId = loginUser.getId();
        //调用orderService的createOrder(cart, userId)
        String orderId = orderService.createOrder(cart, userId);//生成了订单号

        request.getSession().setAttribute("orderId",orderId);//保存到域，将订单号携带给页面，显示出来

        //请求转发
        //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");

    }


}
