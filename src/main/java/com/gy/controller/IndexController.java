package com.gy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gy.dao.BlogDao;
import com.gy.entity.Comment;
import com.gy.queryvo.DetailedBlog;
import com.gy.queryvo.FirstPageBlog;
import com.gy.queryvo.RecommendBlog;
import com.gy.queryvo.ShowBlog;
import com.gy.service.BlogService;
import com.gy.service.CommentService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// TODO 需要进行阿里云的cdn加速
/**
 * @Description: 首页控制器
 * @Author: ye
 * @Date: Created in 13:59 2021/9/1
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThreadPoolExecutor threadPool;

    @Autowired
    private StringRedisTemplate redisTemplate;
    //    首页中的分页查询博客列表
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, RedirectAttributes attributes)
            throws ExecutionException, InterruptedException {
        /**
         * 使用异步编排进行优化代码
         */
        CompletableFuture<List<FirstPageBlog>> task1 = CompletableFuture.supplyAsync(() -> {
            PageHelper.startPage(pageNum, 5);
            List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
            return allFirstPageBlog;
        }, threadPool);

        //返回最新推荐的博客取出前4条
        CompletableFuture<List<RecommendBlog>> task2 = CompletableFuture.supplyAsync(() -> {
            List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
            return recommendedBlog;
        }, threadPool);

        CompletableFuture.allOf(task1, task2).get();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(task1.get());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendedBlogs", task2.get());
        model.addAttribute("n", 1);
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

    /**
     * 使用异步编排进行代码的优化
     * @param id
     * @param model
     * @return
     */
    //   从首页跳转博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model, @RequestParam(value = "out_trade_no", defaultValue = "") String tradeNo)
            throws ExecutionException, InterruptedException {
        // 查询该id的博客信息
        ShowBlog blog = blogService.getBlogById(id);
        // 支付成功会发送交易号,判断交易号是否为null,交易号不为空与需要付费说明支付过了
        if (!StringUtils.isEmpty(tradeNo) && blog.isPay()) {
            // 购买成功会走这个逻辑，将其添加到redis中
            redisTemplate.opsForValue().set("trade_no" + id, String.valueOf(tradeNo), 1, TimeUnit.DAYS);
            return blogCommon(id, model);
        } else if (!blog.isPay()) {
            return blogCommon(id, model);
        } else {
            // 说明不是免费的博客内容和没有支付过
            return "redirect:/vipblog";
        }
    }

    // 抽取一个相同的方法
    public String blogCommon(Long id, Model model) throws ExecutionException, InterruptedException {
        CompletableFuture<DetailedBlog> task1 = CompletableFuture.supplyAsync(() -> {
            DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
            model.addAttribute("blog", detailedBlog);
            return detailedBlog;
        }, threadPool);

        CompletableFuture<List<Comment>> task2 = CompletableFuture.supplyAsync(() -> {
            List<Comment> comments = commentService.listCommentByBlogId(id);
            model.addAttribute("comments", comments);
            return comments;
        }, threadPool);

        CompletableFuture.allOf(task1, task2).get();
        return "blog";
    }

    //    博客被访问信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model) throws ExecutionException, InterruptedException {

        /**
         * 使用异步编排进行优化
         */
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int blogTotal = blogService.getBlogTotal();
            model.addAttribute("blogTotal", blogTotal);
            return blogTotal;
        }, threadPool);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int blogViewTotal = blogService.getBlogViewTotal();
            model.addAttribute("blogViewTotal", blogViewTotal);
            return blogViewTotal;
        }, threadPool);

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            int blogCommentTotal = blogService.getBlogCommentTotal();
            model.addAttribute("blogCommentTotal", blogCommentTotal);
            return blogCommentTotal;
        }, threadPool);

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            int blogMessageTotal = blogService.getBlogMessageTotal();
            model.addAttribute("blogMessageTotal", blogMessageTotal);
            return blogMessageTotal;
        }, threadPool);

        CompletableFuture.allOf(future1, future2, future3, future4).get();

        // 局部刷新fragment
        return "commons/footer :: blogMessage";
    }
}