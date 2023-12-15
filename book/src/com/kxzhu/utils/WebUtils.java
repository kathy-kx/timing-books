package com.kxzhu.utils;

import com.kxzhu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author kxzhu
 * @create 2022-10-03 16:29
 */
public class WebUtils {
    /**
     * 把Map中的值注入到JavaBean属性中
     * 本来用的是request，但是Service层和DAO层没有HttpServletRequest这个API，所以不好;改用Map value作为参数
     * 返回泛型T类型，将注入后的bean对象（泛型T类型）返回
     * @param value
     * @param bean
     */
    public static <T>  T copyParamToBean(Map value, T bean){
        try {
            //System.out.println("注入之前：" + bean);

            BeanUtils.populate(bean,value);//把所有请求的参数 都注入到user对象中
            //System.out.println("注入之后：" + bean);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化为int型数据
     * @param strInt 需要转换的字符串
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
