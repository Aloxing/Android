package com.cn.miraclestar.service.sql_service;

import com.cn.miraclestar.service.logic_service.callbacks.FriendRelationshipCallBack;
import com.cn.miraclestar.sql.entity.FriendRelationship;

public interface FriendRelationshipService {
    void insertFriendRelationship(String token, FriendRelationship friendRelationship, FriendRelationshipCallBack friendRelationshipCallBack);
}
