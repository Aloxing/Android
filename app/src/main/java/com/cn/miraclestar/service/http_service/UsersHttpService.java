package com.cn.miraclestar.service.http_service;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UsersHttpService {
    @GET("/users/{id}")
    Call<Result<Users>> getUser(@Header("Authorization") String authorization, @Path("id") Long id);
}
