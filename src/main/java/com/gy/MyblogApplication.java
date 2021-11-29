package com.gy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyblogApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }
    /**
     * springboot项目打包成war包，使用外置的tomcat启动
     * 1、首先需要继承 org.springframework.boot.context.web.SpringBootServletInitializer类
     * 2、然后重写configure(SpringApplicationBuilder builder)方法
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
