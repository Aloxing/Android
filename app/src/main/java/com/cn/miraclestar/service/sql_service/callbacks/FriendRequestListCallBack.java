package com.cn.miraclestar.service.sql_service.callbacks;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.FriendRequest;

import java.util.List;

public interface FriendRequestListCallBack {

    void onSuccess(Result<List<FriendRequest>> result);
    void onFailure(String errorMessage);

}
