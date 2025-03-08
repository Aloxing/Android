package com.cn.miraclestar.service.sql_service;

import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestCallBack;
import com.cn.miraclestar.sql.entity.FriendRequest;

public interface FriendRequestService {
    void insertFriendRequest(String token, FriendRequest friendRequest, FriendRequestCallBack friendRequestCallBack);
}
