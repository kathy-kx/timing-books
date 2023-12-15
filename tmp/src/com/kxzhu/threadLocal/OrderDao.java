package com.kxzhu.threadLocal;

/**
 * @ClassName OrderDao
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-23 22:05
 */
public class OrderDao {

    public void saveOrder(){
        String name = Thread.currentThread().getName();
        System.out.println("OrderDao当前线程[" + name + "]中保存的数据是:" + ThreadLocalTest.threadLocal.get());

    }
}
