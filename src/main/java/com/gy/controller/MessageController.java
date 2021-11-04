package com.gy.controller;

import com.gy.entity.Message;
import com.gy.entity.User;
import com.gy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 留言页面控制器
 * @Date: Created in 10:57 2021/8/1
 * @Author: ye
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    //跳转到留言页面
    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("n", 6);
        return "message";
    }

    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //    新增留言
    @PostMapping("/message")
    public String post(Message message, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        //设置头像
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
//        Message mg = message.getParentMessage();
//        if (mg != null && mg.getId() != null) {
//            message.setParentMessageId(message.getParentMessage().getId());
//        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }

    //    删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes, Model model) {
        messageService.deleteMessage(id);
        return "redirect:/message";
    }

}