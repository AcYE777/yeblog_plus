package com.gy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gy.entity.Type;
import com.gy.queryvo.FirstPageBlog;
import com.gy.service.BlogService;
import com.gy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * @Description: 分类页面显示控制器
 * @Date: Created in 19:57 2021/8/2
 * @Author: ye
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;
    //    分页查询分类
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllTypeAndBlog();
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5); // 必须在查询条件的前面
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("n",2);

        return "types";
    }

}