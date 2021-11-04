package com.gy.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 搜索博客管理列表
 * @Author: ye
 * @Date: Created in 20:11 2021/7/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {

    private String title;
    private Long typeId;

}