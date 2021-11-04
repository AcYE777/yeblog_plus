package com.gy.service;

import com.gy.entity.User;

/**
 * @Description: 用户业务层接口
 * @Author: ye
 * @Date: Created in 10:59 2021/8/26
 */
public interface UserService {

//    核对用户名和密码
    User checkUser(String username, String password);

    User giteeLogin();
}