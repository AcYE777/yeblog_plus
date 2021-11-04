package com.gy.service.Impl;

import com.alibaba.fastjson.JSON;
import com.gy.NotFoundException;
import com.gy.dao.BlogDao;
import com.gy.entity.Blog;
import com.gy.queryvo.*;
import com.gy.service.BlogService;
import com.gy.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 博客列表业务层接口实现类
 * @Author: ye
 * @Date: Created in 23:31 2021/8/30
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        //设置日期，由于前端没有传来更新日期
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        // 存进去redis中的是一个json格式的字符串
//        String firstPageBlog = redisTemplate.opsForValue().get("FirstPageBlog");
//        if (!StringUtils.isEmpty(firstPageBlog)) {
//            List<FirstPageBlog> firstPageBlogs = JSON.parseArray(firstPageBlog, FirstPageBlog.class);
//            return firstPageBlogs;
//        } else {
//            List<FirstPageBlog> firstPageBlog1 = blogDao.getFirstPageBlog();
//            String json = JSON.toJSONString(firstPageBlog1);
//            redisTemplate.opsForValue().set("FirstPageBlog", json, 1, TimeUnit.DAYS);
//            return firstPageBlog1;
//        }
        return blogDao.getFirstPageBlog();

    }

    /**
     * 对最新推荐使用Redis进行缓存
     * @return
     */
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        String recommendBlog = redisTemplate.opsForValue().get("RecommendBlog");
        if (!StringUtils.isEmpty(recommendBlog)) {
            List<RecommendBlog> recommendBlogs = JSON.parseArray(recommendBlog, RecommendBlog.class);
            return recommendBlogs;
        } else {
            List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
            String json = JSON.toJSONString(allRecommendBlog);
            redisTemplate.opsForValue().set("RecommendBlog", json, 1, TimeUnit.DAYS);
            return allRecommendBlog;
        }
//        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
//        return allRecommendBlog;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
//        文章访问数量自增
        blogDao.updateViews(id);
//        文章评论数量更新
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    /**
     * 获取付费的博客内容
     * @return
     */
    @Override
    public List<FirstPageBlog> getVipBlog() {
        return blogDao.getVipBlog();
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public Integer getBlogTotal() {
       return blogDao.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
       return blogDao.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
       return blogDao.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }

}