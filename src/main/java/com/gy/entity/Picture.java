package com.gy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 照片墙实体类
 * @Author: ye
 * @Date: Created in 9:27 2021/8/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

    private Long id;
    private String picturename;
    private String picturetime;

    @NotBlank(message = "照片地址不能为空")
    private String pictureaddress;

    private String picturedescription;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", picturename='" + picturename + '\'' +
                ", picturetime='" + picturetime + '\'' +
                ", pictureaddress='" + pictureaddress + '\'' +
                ", picturedescription='" + picturedescription + '\'' +
                '}';
    }
}