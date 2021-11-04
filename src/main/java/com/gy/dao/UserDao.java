package com.gy.dao;

import com.gy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 * @Author: ye
 * @Date: Created in 11:11 2021/8/26
 */
@Mapper
@Repository
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    User selectOne();
}