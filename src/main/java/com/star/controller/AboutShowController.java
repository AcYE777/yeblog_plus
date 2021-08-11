package com.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 关于我界面显示控制器
 * @Date: Created in 11:03 2021/8/1
 * @Author: ye
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("id",8);
        return "about";
    }

}