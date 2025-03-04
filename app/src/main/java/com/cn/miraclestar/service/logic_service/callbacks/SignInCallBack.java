package com.cn.miraclestar.service.logic_service.callbacks;

import com.cn.miraclestar.pojo.Result;

//回调接口
public interface SignInCallBack {
    void onSuccess(Result<String> result);
    void onFailure(String errorMessage);
}
