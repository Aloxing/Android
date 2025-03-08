package com.cn.miraclestar.service.logic_service.callbacks;

import com.cn.miraclestar.pojo.Result;

public interface ObjectCallBack {
    void onSuccess(Result<Short> result);
    void onFailure(String errorMessage);
}
