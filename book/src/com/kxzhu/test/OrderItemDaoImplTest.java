package com.kxzhu.test;

import com.kxzhu.dao.OrderDao;
import com.kxzhu.dao.OrderItemDao;
import com.kxzhu.dao.impl.OrderDaoImpl;
import com.kxzhu.dao.impl.OrderItemDaoImpl;
import com.kxzhu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @ClassName OrderItemDaoImplTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-20 10:32
 */
public class OrderItemDaoImplTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"计算机网络",1,new BigDecimal(79),new BigDecimal(79),"1001234565"));

    }
}