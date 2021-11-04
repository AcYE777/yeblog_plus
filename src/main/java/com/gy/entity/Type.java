package com.gy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类实体类
 * @Author: ye
 * @Date: Created in 9:06 2021/8/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

}