package com.cn.miraclestar.service.http_service;


import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.FriendRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FriendRequestHttpService {
    @POST("/friend")
    Call<Result<FriendRequest>> postFriendRequest(@Header("Authorization") String authorization, @Body FriendRequest friendRequest);
}
