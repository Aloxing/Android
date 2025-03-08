package com.cn.miraclestar.service.http_service;


import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.FriendRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FriendRequestHttpService {
    @POST("/friend")
    Call<Result<FriendRequest>> postFriendRequest(@Header("Authorization") String authorization, @Body FriendRequest friendRequest);

    @GET("/friend")
    Call<Result<List<FriendRequest>>> getFriendRequest(@Header("Authorization") String authorization, @Query("userId") Long userId);

    @DELETE("/friend")
    Call<Result<Long>> deleteFriendRequest(@Header("Authorization") String authorization, @Query("userId") Long userId);
}
