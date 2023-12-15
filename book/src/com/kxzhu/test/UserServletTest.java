package com.kxzhu.test;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author kxzhu
 * @create 2022-10-03 09:46
 */

public class UserServletTest {

    public void login(){
        System.out.println("这是login()方法调用了");
    }


    public void regist(){
        System.out.println("这是regist()方法调用了");
    }


    public void updateUser(){
        System.out.println("这是updateUser()方法调用了");
    }


    public void updateUserPassword(){
        System.out.println("这是updateUserPassword()方法调用了");
    }

    public static void main(String[] args) {
        String action = "updateUserPassword";

        Method method = null;
        try {
            method = UserServletTest.class.getDeclaredMethod(action);
            //System.out.println(method);
            method.invoke(new UserServletTest());//调用目标业务方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        //method.setAccessible(true);


    }
}
