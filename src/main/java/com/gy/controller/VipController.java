package com.gy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gy.queryvo.FirstPageBlog;
import com.gy.service.BlogService;
import com.gy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class VipController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/vipblog")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5); // 必须在查询条件的前面
        List<FirstPageBlog> blogs = blogService.getVipBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("n",10);
        return "vip";
    }
}
