package com.kxzhu.test;

import com.kxzhu.pojo.User;
import com.kxzhu.service.UserService;
import com.kxzhu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author kxzhu
 * @create 2022-09-22 18:11
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "keli","keli123","keli@qq.com"));
        userService.registUser(new User(null, "qiqi","qiqi123","qiqi77@qq.com"));
    }

    @Test
    public void login() {
        User userLogIn = userService.login(new User(null, "keli", "keli123", "keli@qq.com"));
        System.out.println(userLogIn);
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("keli")){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}
