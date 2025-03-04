package com.cn.miraclestar.service.logic_service;

import com.cn.miraclestar.service.logic_service.callbacks.TokenCallBack;

public interface TokenLogicService {

    void tokenTrue(String token, TokenCallBack tokenCallBack);

}
