package com.kxzhu.threadLocal;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName ThreadLocal
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-23 17:04
 */
public class ThreadLocalTest {
    //public final static Map<String, Object> data = new Hashtable<String, Object>();
    //ConcurrentHashMap是一个线程安全的HashMap，在高并发下使用。也可以用Hashtable
        //替换为：
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    //ThreadLocal在存的时候 key默认是当前线程；泛型使用value的类型

    private static Random random = new Random();

    //内部类：
    public static class Task implements Runnable{

        @Override
        public void run() {
            //在run()方法中，随机生成一个变量（线程要关联的数据），以当前线程名为key保存到map中
            Integer i = random.nextInt(1000);//0～999的随机数
            //获取当前线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程[" + name + "]生成的随机数是：" + i);
            //data.put(name,i);
            //替换为：
            threadLocal.set(i);


            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().createOrder();
            //关联数据后，数据仍然可以传递进调用函数，关联性不变

            //在run方法结束之前，以当前线程名获取出数据并打印。查看是否可以取出操作
            //Object o = data.get(name);
            //取的时候 替换为：
            Object o = threadLocal.get();
            System.out.println("在线程[" + name + "]快结束时，取出关联的数据是：" + o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++){
            new Thread(new Task()).start();
        }
    }


}
