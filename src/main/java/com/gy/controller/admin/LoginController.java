package com.gy.controller.admin;

import com.gy.components.SendCodeComponent;
import com.gy.entity.User;
import com.gy.entity.vo.LoginVo;
import com.gy.service.UserService;
import com.gy.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 用户登录控制器
 * @Author: ye
 * @Date: Created in 9:54 2021/8/31
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SendCodeComponent sendCodeComponent;
    @Autowired
    private ThreadPoolExecutor threadPool;
    @ResponseBody
    @GetMapping("/sms/sendCode")
    public R sendCode(String phone) {
        // 根据phone作为key从redis中获取value
        String s = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(s)) {
            Long s1 = Long.parseLong(s.split("_")[1]);
            Long sub = System.currentTimeMillis() - s1;
            if (sub < 60000) {
                return R.error(10000, "验证码防刷");
            }
        }
        // 添加上系统时间为了防刷
        String code = UUID.randomUUID().toString().substring(0,5) + "_" + System.currentTimeMillis();

        // 加入redis并设置过期时间,防止60秒内再次发送验证码
        redisTemplate.opsForValue().set(phone, code, 10, TimeUnit.MINUTES);
        // 将手机号和截取的验证码进行发送
        sendCodeComponent.sendCode(phone, code.split("_")[0]);
        return R.ok();
    }


    @GetMapping("/gitee/success")
    public String giteeLogin(@RequestParam("code") String code, HttpSession session) {
        // TODO
        User user = userService.giteeLogin();
        user.setPassword(null);
        session.setAttribute("user", user);
        return "admin/index";
    }

    /**
     * @Description: 跳转到登录页
     * @Author: ye
     * @Date: Created in 9:54 2021/7/31
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * @Description: 登录校验,使用了异步编排
     * @Auther: ye
     * @Date: 10:04 2021/10/29
     * @Param: session:session域
     * @Param: attributes:返回页面消息
     * @Return: 登录成功跳转登录成功页面，登录失败返回登录页面
     */
    @PostMapping("/login")
    public String login(@Valid LoginVo loginVo,
                        HttpSession session,
                        RedirectAttributes attributes) throws ExecutionException, InterruptedException {
        CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> {
            User user = userService.checkUser(loginVo.getUsername(), loginVo.getPassword());
            return user;
        }, threadPool);

        CompletableFuture<String> stringFuture = CompletableFuture.supplyAsync(() -> {
            // 从redis中获取验证码
            String s = redisTemplate.opsForValue().get(loginVo.getPhone());
            return s;
        }, threadPool);

        CompletableFuture.allOf(userFuture, stringFuture).get();

        if (userFuture.get() != null && loginVo.getCode().equals(stringFuture.get().split("_")[0])) {
            userFuture.get().setPassword(null);
            session.setAttribute("user",userFuture.get());
            return "admin/index";
        } else if (!loginVo.getCode().equals(stringFuture.get().split("_")[0])){
            attributes.addFlashAttribute("message", "验证码输入错误");
            return "redirect:/admin";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * @Description: 注销
     * @Auther: ye
     * @Date: 10:15 2021/7/31
     * @Param: session:session域
     * @Return: 返回登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}