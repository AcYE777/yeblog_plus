package com.star.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.dao.BlogDao;
import com.star.entity.Comment;
import com.star.queryvo.DetailedBlog;
import com.star.queryvo.FirstPageBlog;
import com.star.queryvo.RecommendBlog;
import com.star.service.BlogService;
import com.star.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @Description: 首页控制器
 * @Author: ye
 * @Date: Created in 13:59 2021/8/1
 */
@Controller
public class IndexController {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogService blogService;

//    @Autowired
//    private TypeService typeService;

    @Autowired
    private CommentService commentService;

//    首页中的分页查询博客列表
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, RedirectAttributes attributes){
        PageHelper.startPage(pageNum,5);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        //返回最新推荐的博客取出前4条
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendedBlog);
        model.addAttribute("id",1);
        return "index";
    }

//    搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

//   从首页跳转博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

//    最新博客列表
//    @GetMapping("/footer/newblog")
//    public String newblogs(Model model) {
//        List<FirstPageBlog> newBlog = blogService.getNewBlog();
//        model.addAttribute("newblogs", newBlog);
//        return "index :: newblogList";
//    }

//    博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "commons/footer :: blogMessage";
    }
}