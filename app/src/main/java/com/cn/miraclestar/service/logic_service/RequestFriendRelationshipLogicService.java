package com.cn.miraclestar.service.logic_service;

import com.cn.miraclestar.service.logic_service.callbacks.RequestFriendRelationshipCallBack;

public interface RequestFriendRelationshipLogicService {
    void getFriendList(String token, Long userId, RequestFriendRelationshipCallBack requestFriendRelationshipCallBack);
}
