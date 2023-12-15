package com.kxzhu.test;

import com.kxzhu.dao.UserDao;
import com.kxzhu.dao.impl.UserDaoImpl;
import com.kxzhu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试UserDaoImpl实现类的方法
 * @author kxzhu
 * @create 2022-09-22 16:21
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl(); //userDao对象有UserDaoImpl类的方法

    @Test
    public void queryUsersByUsernameTest() {

        if( userDao.queryUsersByUsername("admin123") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUsersByUsernameAndPasswordTest() {
        if (userDao.queryUsersByUsernameAndPassword("admin1","admin") == null){
            System.out.println("用户名或密码错误，登录失败");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUserTest() {
        User user = new User(null, "hutao", "hutao123", "hutao@qq.com");//id值自增，用null填充
        System.out.println(userDao.saveUser(user));
    }
}