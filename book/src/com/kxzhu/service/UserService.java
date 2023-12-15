package com.kxzhu.service;

import com.kxzhu.pojo.User;

/**
 * 一个业务是一个方法
 * service负责三个业务：查用户名是否重复、注册、登录
 * @author kxzhu
 * @create 2022-09-22 17:34
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null，说明用户名或密码错误；返回用户，标明有这个用户名密码的用户，登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return  返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);

}
