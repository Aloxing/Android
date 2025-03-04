package com.cn.miraclestar.service.http_service;

import com.cn.miraclestar.pojo.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TokenHttpService {

    @GET("/token")
    Call<Result<String>> tokenTrue(@Query("token") String token);
}
