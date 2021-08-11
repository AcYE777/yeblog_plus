package com.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 音乐盒页面显示控制器
 * @Date: Created in 10:55 2020/8/11
 * @Author: ye
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String music(Model model) {
        model.addAttribute("id",4);
        return "music";
    }

}