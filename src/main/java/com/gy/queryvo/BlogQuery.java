package com.gy.queryvo;

import com.gy.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 显示数据实体类
 * @Author: ye
 * @Date: Created in 15:20 2021/7/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {

    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;

}