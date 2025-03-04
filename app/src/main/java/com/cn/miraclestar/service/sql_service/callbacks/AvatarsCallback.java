package com.cn.miraclestar.service.sql_service.callbacks;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.Avatars;
import com.cn.miraclestar.sql.entity.Users;

public interface AvatarsCallback {
    void onSuccess(Result<Avatars> result);
    void onSetAvatarsSuccess(Result<String> result);
    void onFailure(String errorMessage);
}
