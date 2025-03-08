package com.cn.miraclestar.service.sql_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.FriendRelationshipHttpService;
import com.cn.miraclestar.service.logic_service.callbacks.FriendRelationshipCallBack;
import com.cn.miraclestar.service.sql_service.FriendRelationshipService;
import com.cn.miraclestar.sql.entity.FriendRelationship;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FriendRelationshipServiceImpl implements FriendRelationshipService {
    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final FriendRelationshipHttpService  _friend_relationship_http_service = _retrofit.create(FriendRelationshipHttpService.class);
    @Override
    public void insertFriendRelationship(String token, FriendRelationship friendRelationship, FriendRelationshipCallBack friendRelationshipCallBack) {
        Call<Result<FriendRelationship>> call = _friend_relationship_http_service.insertFriendRelationship(token,friendRelationship);
        call.enqueue(new Callback<Result<FriendRelationship>>() {
            @Override
            public void onResponse(Call<Result<FriendRelationship>> call, Response<Result<FriendRelationship>> response) {
                if(response.isSuccessful())
                   friendRelationshipCallBack.onSuccess(response.body());
                else
                    friendRelationshipCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<FriendRelationship>> call, Throwable t) {
                friendRelationshipCallBack.onFailure("Network error: "+t.getMessage());
            }
        });
    }
}
