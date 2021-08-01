package com.star.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客实体类
 * @Author: ye
 * @Date: Created in 22:58 2021/7/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Long id;
    private boolean appreciation;
    private boolean commentabled;
    private String content;
    private Date createTime;
    private String description;
    private String firstPicture;
    private String flag; //版权开启
    private boolean published;
    private boolean recommend;
    private boolean shareStatement;
    private String title;
    private Date updateTime;
    private Integer views;
    private Long typeId;
    private Long userId;
    private Integer commentCount;

    private Type type;
    private User user;
    private List<Comment> comments = new ArrayList<>();

}