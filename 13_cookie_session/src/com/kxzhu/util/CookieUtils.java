package com.kxzhu.util;

import javax.servlet.http.Cookie;

/**
 * @ClassName CookieUtils
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-13 16:29
 */
public class CookieUtils {
    /**
     * 在cookies中查找指定名称name的cookie对象
     * @param name 所要查找cookie的名称（键）
     * @param cookies 从哪里查找，查找的范围
     * @return
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        if(name == null || cookies == null || cookies.length == 0 ){
            return  null;
        }else {
            for (Cookie cookie: cookies){
                if(name.equals(cookie.getName())){
                    return cookie;//就不用写break了，return后不再执行循环
                }
            }
            return null;
        }


    }
}
