package com.gy.controller;

import com.gy.entity.Comment;
import com.gy.entity.User;
import com.gy.queryvo.DetailedBlog;
import com.gy.service.BlogService;
import com.gy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 评论控制器
 * @Author: ye
 * @Date: Created in 17:16 2021/8/1
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    //这个值来自于配置文件application.yml
    @Value("${comment.avatar}")
    private String avatar;

    //    查询评论列表
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //    新增评论,此处修改过
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session,Model model) {
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            //设置头像
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

//    删除评论 /comment/{param1}/{param2}/delete前端路径,只要这里的GetMapping写了属性就可以用comment接收
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id,Comment comment, Model model){
        System.out.println(comment);
        //删除该评论同时更新博客表
        commentService.deleteComment(comment,id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }

}