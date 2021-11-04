package com.gy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 协同维护实体类
 * @Author: ye
 * @Date: Created in 9:25 2021/8/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {

    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;
    @Override
    public String toString() {
        return "FriendLink{" +
                "id=" + id +
                ", blogname='" + blogname + '\'' +
                ", blogaddress='" + blogaddress + '\'' +
                ", pictureaddress='" + pictureaddress + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}