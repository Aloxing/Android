package com.cn.miraclestar.service.sql_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.FriendRequestHttpService;
import com.cn.miraclestar.service.sql_service.FriendRequestService;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestCallBack;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestListCallBack;
import com.cn.miraclestar.service.sql_service.callbacks.FriendRequestLongCallBack;
import com.cn.miraclestar.sql.entity.FriendRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FriendRequestServiceImpl implements FriendRequestService {
    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final FriendRequestHttpService _friend_request_http_service = _retrofit.create(FriendRequestHttpService.class);
    @Override
    public void insertFriendRequest(String token, FriendRequest friendRequest, FriendRequestCallBack friendRequestCallBack) {

        Call<Result<FriendRequest>> call = _friend_request_http_service.postFriendRequest(token,friendRequest);

        call.enqueue(new Callback<Result<FriendRequest>>() {
            @Override
            public void onResponse(Call<Result<FriendRequest>> call, Response<Result<FriendRequest>> response) {
                if(response.isSuccessful())
                    friendRequestCallBack.onSuccess(response.body());
                else
                    friendRequestCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<FriendRequest>> call, Throwable t) {
                friendRequestCallBack.onFailure("Network error: "+t.getMessage());
            }
        });
    }

    @Override
    public void getFriendRequestList(String token, Long userId, FriendRequestListCallBack friendRequestListCallBack) {
        Call<Result<List<FriendRequest>>> call = _friend_request_http_service.getFriendRequest(token,userId);

        call.enqueue(new Callback<Result<List<FriendRequest>>>() {
            @Override
            public void onResponse(Call<Result<List<FriendRequest>>> call, Response<Result<List<FriendRequest>>> response) {
                if(response.isSuccessful())
                    friendRequestListCallBack.onSuccess(response.body());
                else
                    friendRequestListCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<List<FriendRequest>>> call, Throwable t) {
                friendRequestListCallBack.onFailure("Network error: "+t.getMessage());
            }
        });
    }

    @Override
    public void deleteFriendRequest(String token, Long userId, FriendRequestLongCallBack friendRequestLongCallBack) {
        Call<Result<Long>> call = _friend_request_http_service.deleteFriendRequest(token,userId);
        call.enqueue(new Callback<Result<Long>>() {
            @Override
            public void onResponse(Call<Result<Long>> call, Response<Result<Long>> response) {
                if(response.isSuccessful())
                    friendRequestLongCallBack.onSuccess(response.body());
                else
                    friendRequestLongCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<Long>> call, Throwable t) {
                friendRequestLongCallBack.onFailure("Network error: "+t.getMessage());
            }
        });
    }
}
