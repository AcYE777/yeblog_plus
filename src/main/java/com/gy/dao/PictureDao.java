package com.gy.dao;

import com.gy.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 照片墙持久层接口
 * @Date: Created in 23:32 2021/8/16
 * @Author: ye
 */
@Mapper
@Repository
public interface PictureDao {

    List<Picture> listPicture();

    int savePicture(Picture picture);

    Picture getPicture(Long id);

    int updatePicture(Picture picture);

    void deletePicture(Long id);

}