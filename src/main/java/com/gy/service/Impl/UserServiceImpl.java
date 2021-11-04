package com.gy.service.Impl;

import com.gy.dao.UserDao;
import com.gy.entity.User;
import com.gy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务层接口实现类
 * @Author: ye
 * @Date: Created in 10:59 2021/8/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        //User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        System.out.println(username+"----"+password);
        User user = userDao.findByUsernameAndPassword(username,password);
        System.out.println("------------");
        return user;
    }

    @Override
    public User giteeLogin() {
        return userDao.selectOne();
    }
}