package com.gy.service;

import com.gy.entity.FriendLink;

import java.util.List;

/**
 * @Description: 协同维护业务层接口
 * @Date: Created in 13:57 2021/8/16
 * @Author: ye
 */
public interface FriendLinkService {

    // 查询所有协同维护
    List<FriendLink> listFriendLink();

    // 协同维护新增
    int saveFriendLink(FriendLink friendLink);

    // 根据id查询协同维护
    FriendLink getFriendLink(Long id);

    // 根据网址查询协同维护
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    // 编辑修改协同维护
    int updateFriendLink(FriendLink friendLink);

    // 删除协同维护
    void deleteFriendLink(Long id);


}