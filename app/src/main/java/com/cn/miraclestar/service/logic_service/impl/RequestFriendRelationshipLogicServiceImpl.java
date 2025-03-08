package com.cn.miraclestar.service.logic_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.dto.RequestFriendRelationshipDto;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.RequestFriendRelationshipHttpService;
import com.cn.miraclestar.service.logic_service.RequestFriendRelationshipLogicService;
import com.cn.miraclestar.service.logic_service.callbacks.ObjectCallBack;
import com.cn.miraclestar.service.logic_service.callbacks.RequestFriendRelationshipCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestFriendRelationshipLogicServiceImpl implements RequestFriendRelationshipLogicService {
    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final RequestFriendRelationshipHttpService _request_friend_relationship_http_service = _retrofit.create(RequestFriendRelationshipHttpService.class);

    @Override
    public void getFriendList(String token, Long userId, RequestFriendRelationshipCallBack requestFriendRelationshipCallBack) {

        Call<Result<List<RequestFriendRelationshipDto>>> call = _request_friend_relationship_http_service.getFriendList(token,userId);

        call.enqueue(new Callback<Result<List<RequestFriendRelationshipDto>>>() {
            @Override
            public void onResponse(Call<Result<List<RequestFriendRelationshipDto>>> call, Response<Result<List<RequestFriendRelationshipDto>>> response) {
                if(response.isSuccessful())
                    requestFriendRelationshipCallBack.onSuccess(response.body());
                else
                    requestFriendRelationshipCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<List<RequestFriendRelationshipDto>>> call, Throwable t) {
                requestFriendRelationshipCallBack.onFailure("Network error: " + t.getMessage());
            }
        });

    }

    @Override
    public void getFriendData(String token, Long userId, RequestFriendRelationshipCallBack requestFriendRelationshipCallBack) {
        Call<Result<RequestFriendRelationshipDto>> call = _request_friend_relationship_http_service.getFriendData(token,userId);

        call.enqueue(new Callback<Result<RequestFriendRelationshipDto>>() {
            @Override
            public void onResponse(Call<Result<RequestFriendRelationshipDto>> call, Response<Result<RequestFriendRelationshipDto>> response) {
                if(response.isSuccessful())
                    requestFriendRelationshipCallBack.onSuccessOne(response.body());
                else
                    requestFriendRelationshipCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<RequestFriendRelationshipDto>> call, Throwable t) {
                requestFriendRelationshipCallBack.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getIsFriend(String token, Long userId, ObjectCallBack objectCallBack) {
        Call<Result<Short>> call = _request_friend_relationship_http_service.getIsFriend(token,userId);

        call.enqueue(new Callback<Result<Short>>() {
            @Override
            public void onResponse(Call<Result<Short>> call, Response<Result<Short>> response) {
                if(response.isSuccessful())
                    objectCallBack.onSuccess(response.body());
                else
                    objectCallBack.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<Short>> call, Throwable t) {
                objectCallBack.onFailure("Network error: " + t.getMessage());
            }
        });
    }
}
