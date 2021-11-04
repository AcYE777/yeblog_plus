package com.gy.controller;

import com.gy.queryvo.BlogQuery;
import com.gy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 时间轴页面显示控制器
 * @Date: Created in 9:54 2021/8/2
 * @Author: ye
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        model.addAttribute("n",3);
        return "archives";
    }

}