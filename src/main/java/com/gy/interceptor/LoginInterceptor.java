package com.gy.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录过滤拦截 1.定义一个规则根据session来判断 2.写一个配置类说明拦截的路径
 * @Author: ye
 * @Date: Created in 13:55 2021/8/15
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getRequestURL().indexOf("/admin/sms/sendCode") != -1) {
            return true;
        }

        if (request.getRequestURL().indexOf("/admin/gitee/success") != -1) {
            return true;
        }
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}