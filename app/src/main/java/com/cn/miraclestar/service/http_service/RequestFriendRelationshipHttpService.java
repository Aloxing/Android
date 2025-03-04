package com.cn.miraclestar.service.http_service;

import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestFriendRelationshipHttpService {

    @GET("/friendrelationship/list/{userId}")
    Call<Result<List<RequestFriendRelationshipDto>>> getFriendList(@Header("Authorization") String authorization,@Path("userId") Long userId);

}
