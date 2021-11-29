package com.gy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gy.entity.Blog;
import com.gy.entity.Type;
import com.gy.entity.User;
import com.gy.queryvo.BlogQuery;
import com.gy.queryvo.SearchBlog;
import com.gy.queryvo.ShowBlog;
import com.gy.service.BlogService;
import com.gy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 博客管理控制器
 * @Author: ye
 * @Date: Created in 12:08 2021/8/12
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ThreadPoolExecutor threadPool;
    //博客列表
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("id",1);
        return "admin/blogs";
    }

    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    //博客新增,发布和保存都是走这个的
    @PostMapping("/blogs")
    public String post(@Valid Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser( (User) session.getAttribute("user") );
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);

        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //     删除文章
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //    跳转编辑修改文章
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        List<Type> allType = typeService.getAllType();
        model.addAttribute("types", allType);
        ShowBlog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);

        return "admin/blogs-input";
    }

    //    编辑修改文章
    @PostMapping("/blogs/{id}")
    public String editPost(ShowBlog showBlog, RedirectAttributes attributes) {
        int count = blogService.updateBlog(showBlog);
        if(count == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    //    搜索博客
    @RequestMapping(value = "/blogs/search",method = RequestMethod.POST)
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        //注意使用分页插件顺序不能错，一定是先定义规则然后再进行设置页面的大小
        System.out.println(searchBlog);
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        if (searchBlog.getTypeId() == null && searchBlog.getTitle() == "") {
            model.addAttribute("hasSearch",false);
        } else {
            model.addAttribute("hasSearch",true);
        }
        //可以只对blogs.html页面中的blogList部分进行刷新,需要在blogs.html页面配置th:fragment
    //        return "admin/blogs::blogPart";
        return "admin/blogs::blogPart";
    }
}


