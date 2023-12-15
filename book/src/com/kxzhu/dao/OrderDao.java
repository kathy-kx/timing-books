package com.kxzhu.dao;

import com.kxzhu.pojo.Order;

/**
 * 目前只有【生成订单】部分
 * @ClassName OrderDao
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:18
 */
public interface OrderDao {
    public int saveOrder(Order order);

}
