package com.gy.entity;

import com.gy.queryvo.DetailedBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 评论实体类
 * @Author: ye
 * @Date: Created in 9:09 2021/7/31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;

    //头像
    private String avatar;
    private Date createTime;

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    //回复评论,存放当前评论的子评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment; //没用上
    private boolean adminComment;

    private DetailedBlog blog;

}