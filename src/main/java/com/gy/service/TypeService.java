package com.gy.service;

import com.gy.entity.Type;

import java.util.List;


/**
 * @Description: 文章分类业务层接口
 * @Author: ye
 * @Date: Created in 14:32 2021/8/27
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type>getAllType();

    List<Type>getAllTypeAndBlog();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);




}