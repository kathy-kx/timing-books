package com.kxzhu.test;

import com.kxzhu.dao.OrderDao;
import com.kxzhu.dao.impl.OrderDaoImpl;
import com.kxzhu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @ClassName OrderDaoImplTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:32
 */
public class OrderDaoImplTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrderTest() {
        orderDao.saveOrder(new Order("1001234565",new Date(),new BigDecimal(79.2),0,1));
    }
}