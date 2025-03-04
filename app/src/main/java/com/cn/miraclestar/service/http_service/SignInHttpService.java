package com.cn.miraclestar.service.http_service;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

//发起请求
public interface SignInHttpService {
    @GET("/sign/in")
    @Headers("Accept-Charset: UTF-8")
    Call<Result<String>> signIn(
            @Query("username")String username,
            @Query("password")String password
    );

}
