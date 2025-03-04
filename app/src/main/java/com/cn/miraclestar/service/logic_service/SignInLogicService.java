package com.cn.miraclestar.service.logic_service;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.logic_service.callbacks.SignInCallBack;

public interface SignInLogicService {
    void signIn(String username, String password, SignInCallBack callBack);
}
