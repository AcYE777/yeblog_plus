package com.gy;

import com.gy.dao.BlogDao;
import com.gy.queryvo.RecommendBlog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    BlogDao blogDao;
    @Test
    void contextLoads() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        System.out.println(allRecommendBlog);

    }

}
