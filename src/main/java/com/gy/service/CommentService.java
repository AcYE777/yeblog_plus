package com.gy.service;

import com.gy.entity.Comment;

import java.util.List;

/**
 * @Description: 博客评论业务层接口
 * @Author: ye
 * @Date: Created in 13:26 2021/8/5
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    //删除评论
    void deleteComment(Comment comment,Long id);

}