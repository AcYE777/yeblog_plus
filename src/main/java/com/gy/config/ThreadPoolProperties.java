package com.gy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ye
 * @Date: Created in 23:16 2021/10/31
 */
@ConfigurationProperties(prefix = "yeblog.threadpool")
@Data
public class ThreadPoolProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
