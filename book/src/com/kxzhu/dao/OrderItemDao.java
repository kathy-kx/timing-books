package com.kxzhu.dao;

import com.kxzhu.pojo.Order;
import com.kxzhu.pojo.OrderItem;

/**
 * 目前只有【生成订单】部分
 * @ClassName OrderItemDao
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:19
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
