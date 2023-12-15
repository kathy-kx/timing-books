package com.kxzhu.service.impl;

import com.kxzhu.dao.BookDao;
import com.kxzhu.dao.OrderDao;
import com.kxzhu.dao.OrderItemDao;
import com.kxzhu.dao.impl.BookDaoImpl;
import com.kxzhu.dao.impl.OrderDaoImpl;
import com.kxzhu.dao.impl.OrderItemDaoImpl;
import com.kxzhu.pojo.*;
import com.kxzhu.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 21:18
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //保存订单//保存订单项

        //订单号：保证唯一性
        String orderId = System.currentTimeMillis() + "" + userId;

        Order order = new Order(orderId, new Date(), cart.getTotalPrice(),0, userId);
        orderDao.saveOrder(order);//保存订单

        int i = 12/0;//一个异常

        //购物车里所选的商品项就是订单项
        //遍历购物车中每一个商品项cartItem 转换成为订单项orderItem保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();// 获取每一个购物车中的商品项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),cartItem.getTotalPrice(),orderId);// 转换为每一个订单项
            orderItemDao.saveOrderItem(orderItem);// 保存订单项到数据库

            //更新库存和销量
            //下订单后需要减库存、增销量：
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getSales() - cartItem.getCount() );
            bookDao.updateBook(book);
        }

        cart.clear();// 清空购物车


        return orderId;
    }
}
