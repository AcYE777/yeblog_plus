package com.gy.controller;

import com.gy.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 协同管理显示控制器
 * @Date: Created in 13:54 2021/8/16
 */
@Controller
public class FriendsShowController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("friendlinks",friendLinkService.listFriendLink());
        model.addAttribute("n",7);
        return "friends";
    }

}