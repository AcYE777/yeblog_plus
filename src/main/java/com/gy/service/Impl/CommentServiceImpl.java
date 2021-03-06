package com.gy.service.Impl;

import com.gy.dao.BlogDao;
import com.gy.dao.CommentDao;
import com.gy.entity.Comment;
import com.gy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客评论业务层接口实现类
 * @Author: ye
 * @Date: Created in 13:26 2021/8/5
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出父节点, 注意parent_comment值为-1的算是父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        //遍历父节点
        for(Comment comment : comments){
            Long id = comment.getId(); //获取父节点的id
            String parentNickname1 = comment.getNickname();//获取父节点的昵称
            //查询出该父亲的所有孩子,一对多的关系
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId,id);
//            查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            //这个tempReplys存放当前的子回复
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                //这里是由于Comment里面有一个parentNickname，需要赋值
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    private void recursively(Long blogId, Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

//    新增评论
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
//        文章评论计数,在查询id的同时进行更新t_blogs表中的comment_count字段
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

//    删除评论
    @Override
    public void deleteComment(Comment comment,Long id) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
    }
}