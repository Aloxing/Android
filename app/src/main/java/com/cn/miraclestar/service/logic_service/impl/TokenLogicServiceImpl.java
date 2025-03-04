package com.cn.miraclestar.service.logic_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.TokenHttpService;
import com.cn.miraclestar.service.logic_service.TokenLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.TokenCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TokenLogicServiceImpl implements TokenLogicService {

    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final TokenHttpService _token_http_service = _retrofit.create(TokenHttpService.class);
    @Override
    public void tokenTrue(String token, TokenCallBack tokenCallBack) {
        Call<Result<String>> call = _token_http_service.tokenTrue(token);
        call.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if(response.isSuccessful())
                    tokenCallBack.onSuccess(response.body());
                else
                    tokenCallBack.onFailure("Request failed: " + response.code());
            }
            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                tokenCallBack.onFailure("Network error: " + t.getMessage());
            }
        });
    }
}
