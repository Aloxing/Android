package com.cn.miraclestar.service.sql_service.callbacks;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.Users;

public interface UsersCallback {
    void onSuccess(Result<Users> result);
    void onFailure(String errorMessage);
}
