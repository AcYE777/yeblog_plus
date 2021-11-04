package com.gy.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.StringUtils;
import com.gy.config.AlipayTemplate;
import com.gy.entity.vo.PayVo;
import com.gy.queryvo.ShowBlog;
import com.gy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PayController {

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private BlogService blogService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 买家账号 vhlgcr1120@sandbox.com
     * 登录密码 111111
     * @param blogId
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @GetMapping(value = "/pay/zhifubao", produces = "text/html")
    public String pay(@RequestParam("blogId") String blogId,
                      HttpServletRequest request,
                      HttpServletResponse response)
            throws AlipayApiException, IOException {
        PayVo payVo = new PayVo();
        // 需要查询该博客的详细信息
        ShowBlog blogEntity = blogService.getBlogById(Long.valueOf(blogId));
        payVo.setSubject(blogEntity.getTitle() +" 有效时间为一天");
        payVo.setBody(blogEntity.getDescription());
        payVo.setTotal_amount("20");
        // 这里的订单号从redis中获取
        String s = redisTemplate.opsForValue().get("trade_no" + blogId);
        if (!StringUtils.isEmpty(s)) {
            // 说明已经支付过
            // 有ResponseBody注解不能使用return "redirect:"+"http://"+request.getServerName()+":"+ request.getServerPort() +"/blog/"+blogId;
            response.sendRedirect("http://"+request.getServerName()+":"+ request.getServerPort() +"/blog/"+blogId+"?out_trade_no="+s);
        }
        Long tradeNo = System.currentTimeMillis();
        payVo.setOut_trade_no(String.valueOf(tradeNo));
        // redisTemplate.opsForValue().set("trade_no" + blogId, String.valueOf(tradeNo), 720, TimeUnit.HOURS);
        // redis的赋值见IndexController的blog方法
        alipayTemplate.return_url = "http://"+request.getServerName()+":"+ request.getServerPort() +"/blog/"+blogId;
        String pay = alipayTemplate.pay(payVo);
        return pay;
    }
}
