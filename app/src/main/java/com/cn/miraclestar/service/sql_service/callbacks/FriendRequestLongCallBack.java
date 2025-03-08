package com.cn.miraclestar.service.sql_service.callbacks;

import com.cn.miraclestar.pojo.Result;

public interface FriendRequestLongCallBack {
    void onSuccess(Result<Long> result);
    void onFailure(String errorMessage);
}
