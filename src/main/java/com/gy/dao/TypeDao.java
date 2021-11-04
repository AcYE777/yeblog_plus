package com.gy.dao;

import com.gy.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: ye
 * @Description: 文章分类持久层接口
 * @Date: Created in 15:14 2021/8/16
 */
@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndBlog();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);


}