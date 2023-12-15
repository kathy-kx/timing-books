package com.kxzhu.dao;

import com.kxzhu.pojo.User;

/**
 * @author kxzhu
 * @create 2022-09-22 10:27
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @return 如果返回null，说明没有这个用户；反之，有这个用户名，返回该用户
     */
    public User queryUsersByUsername(String username);

    /**
     * 根据用户名和密码，查询用户信息
     * @param username
     * @param password
     * @return  如果返回null，说明用户名或密码错误；反之，有这个用户名密码的用户，返回用户
     */
    public User queryUsersByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1，表示操作失败；返回其他，表示sql语句影响的行数
     */
    public int saveUser(User user);
}
