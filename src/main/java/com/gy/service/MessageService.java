package com.gy.service;

import com.gy.entity.Message;

import java.util.List;

/**
 * @Description: 留言业务层接口
 * @Date: Created in 11:23 2021/8/16
 * @Author: ye
 */
public interface MessageService {

    //查询留言列表
    List<Message> listMessage();

    //保存留言
    int saveMessage(Message message);

    //删除留言
    void deleteMessage(Long id);

}