package com.star.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 博客首页数据实体类
 * @Author: ONESTAR
 * @Date: Created in 9:09 2020/4/3
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstPageBlog {

    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;

}