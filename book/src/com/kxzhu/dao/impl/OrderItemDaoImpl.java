package com.kxzhu.dao.impl;

import com.kxzhu.dao.OrderItemDao;
import com.kxzhu.pojo.OrderItem;

/**
 * @ClassName OrderItemDaoImpl
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:21
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

        return 0;
    }
}
