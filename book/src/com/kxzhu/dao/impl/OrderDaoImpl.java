package com.kxzhu.dao.impl;

import com.kxzhu.dao.OrderDao;
import com.kxzhu.pojo.Order;

/**
 * @ClassName OrderDaoImpl
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:21
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     * 保存订单(增 一个订单)
     * @param order
     */
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
        return 0;
    }
}
