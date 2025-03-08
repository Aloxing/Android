package com.cn.miraclestar.service.http_service;

import androidx.room.Insert;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.FriendRelationship;
import com.cn.miraclestar.sql.entity.FriendRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FriendRelationshipHttpService {

    @POST("/friendrelationship")
    Call<Result<FriendRelationship>> insertFriendRelationship(@Header("Authorization") String authorization, @Body FriendRelationship friendRelationship);


}
