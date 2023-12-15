package com.kxzhu.service.impl;

import com.kxzhu.dao.UserDao;
import com.kxzhu.dao.impl.UserDaoImpl;
import com.kxzhu.pojo.User;
import com.kxzhu.service.UserService;

/**
 * @author kxzhu
 * @create 2022-09-22 18:04
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();//用它操作数据库，调用持久层

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUsersByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUsersByUsername(username) == null){
            //没查到该用户名，表示可用
            return false;
        }
        return true;//查到该用户名，用户名已存在，表示不可用
    }
}
