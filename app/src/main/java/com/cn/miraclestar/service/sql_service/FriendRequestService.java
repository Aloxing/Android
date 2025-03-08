package com.cn.miraclestar.service.sql_service;

import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestCallBack;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestListCallBack;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestLongCallBack;
import com.cn.miraclestar.sql.entity.FriendRequest;

public interface FriendRequestService {
    void insertFriendRequest(String token, FriendRequest friendRequest, FriendRequestCallBack friendRequestCallBack);
    void getFriendRequestList(String token, Long userId, FriendRequestListCallBack friendRequestListCallBack);
    void deleteFriendRequest(String token, Long userId, FriendRequestLongCallBack friendRequestLongCallBack);
}
