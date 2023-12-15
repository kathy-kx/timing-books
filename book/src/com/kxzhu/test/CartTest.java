package com.kxzhu.test;

import com.kxzhu.pojo.Cart;
import com.kxzhu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 测试Cart类的方法
 * @ClassName CartTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-17 16:03
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(2,"茶馆",1,new BigDecimal(20),new BigDecimal(20)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(2,"茶馆",1,new BigDecimal(20),new BigDecimal(20)));

        cart.deleteItem(1);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(2,"茶馆",1,new BigDecimal(20),new BigDecimal(20)));

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(1,"城南旧事",1,new BigDecimal(13),new BigDecimal(13)));
        cart.addItem(new CartItem(2,"茶馆",1,new BigDecimal(20),new BigDecimal(20)));

        cart.updateCount(2,10);

        System.out.println(cart);
    }
}