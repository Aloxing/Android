package com.cn.miraclestar.service.logic_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.SignInHttpService;
import com.cn.miraclestar.service.logic_service.SignInLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.SignInCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//实现登录接口类
public class SignInLogicServiceImpl implements SignInLogicService {
    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final SignInHttpService _sign_in_http_service = _retrofit.create(SignInHttpService.class);
    @Override
    public void signIn(String username, String password, SignInCallBack callback) {

        Call<Result<String>> call = _sign_in_http_service.signIn(username,password);

        call.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onFailure("Request failed: " + response.code());
            }
            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

}
