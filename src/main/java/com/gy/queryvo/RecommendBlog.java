package com.gy.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 推荐博客数据实体类
 * @Author: ye
 * @Date: Created in 10:45 2021/8/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend; //是否推荐

}