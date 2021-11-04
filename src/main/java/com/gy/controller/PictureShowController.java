package com.gy.controller;

import com.gy.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 照片墙页面显示控制器
 * @Date: Created in 23:13 2021/8/16
 * @Author: ye
 */
@Controller
public class PictureShowController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public String friends(Model model) {
        model.addAttribute("pictures",pictureService.listPicture());
        model.addAttribute("n",8);
        return "picture";
    }

}