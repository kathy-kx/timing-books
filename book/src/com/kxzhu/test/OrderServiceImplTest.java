package com.kxzhu.test;

import com.kxzhu.pojo.Cart;
import com.kxzhu.pojo.CartItem;
import com.kxzhu.pojo.Order;
import com.kxzhu.pojo.OrderItem;
import com.kxzhu.service.OrderService;
import com.kxzhu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @ClassName OrderServiceImplTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 22:04
 */
public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(2,"茶馆",1,new BigDecimal(20),new BigDecimal(20)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println("订单号是：" + orderService.createOrder(cart,1));


    }
}