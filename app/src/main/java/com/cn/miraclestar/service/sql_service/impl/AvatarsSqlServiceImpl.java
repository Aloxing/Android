package com.cn.miraclestar.service.sql_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.AvatarsHttpService;
import com.cn.miraclestar.service.http_service.UsersHttpService;
import com.cn.miraclestar.service.sql_service.AvatarsSqlService;
import com.cn.miraclestar.service.sql_service.callbacks.AvatarsCallback;
import com.cn.miraclestar.sql.entity.Avatars;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AvatarsSqlServiceImpl implements AvatarsSqlService {

    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final AvatarsHttpService _avatars_http_service = _retrofit.create(AvatarsHttpService.class);
    @Override
    public void getAvatar(String authorization, Long userId, AvatarsCallback avatarsCallback) {

        Call<Result<Avatars>> call = _avatars_http_service.getAvatar(authorization,userId);

        call.enqueue(new Callback<Result<Avatars>>() {
            @Override
            public void onResponse(Call<Result<Avatars>> call, Response<Result<Avatars>> response) {
                if(response.isSuccessful())
                    avatarsCallback.onSuccess(response.body());
                else
                    avatarsCallback.onFailure("Request failed: " + response.code());
            }
            @Override
            public void onFailure(Call<Result<Avatars>> call, Throwable t) {
                avatarsCallback.onFailure("Network error: "+t.getMessage());
            }
        });
    }

    @Override
    public void setAvatar(String authorization, Long userId, MultipartBody.Part image, AvatarsCallback avatarsCallback) {
        Call<Result<String>> call = _avatars_http_service.setAvatar(authorization,userId,image);
        call.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if(response.isSuccessful())
                    avatarsCallback.onSetAvatarsSuccess(response.body());
                else
                    avatarsCallback.onFailure("Request failed: " + response.code());
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                avatarsCallback.onFailure("Network error: "+t.getMessage());
            }
        });
    }
}
