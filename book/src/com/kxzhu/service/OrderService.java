package com.kxzhu.service;

import com.kxzhu.pojo.Cart;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 21:09
 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
