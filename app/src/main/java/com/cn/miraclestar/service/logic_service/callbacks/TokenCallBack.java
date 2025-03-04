package com.cn.miraclestar.service.logic_service.callbacks;

import com.cn.miraclestar.pojo.Result;

public interface TokenCallBack {
    void onSuccess(Result<String> result);
    void onFailure(String errorMessage);
}
