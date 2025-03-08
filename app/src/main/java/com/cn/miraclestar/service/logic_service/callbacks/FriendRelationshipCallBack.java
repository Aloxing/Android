package com.cn.miraclestar.service.logic_service.callbacks;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.FriendRelationship;

public interface FriendRelationshipCallBack {
    void onSuccess(Result<FriendRelationship> result);
    void onFailure(String errorMessage);
}
