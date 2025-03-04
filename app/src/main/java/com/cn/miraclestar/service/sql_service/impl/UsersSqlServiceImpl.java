package com.cn.miraclestar.service.sql_service.impl;

import com.cn.miraclestar.clients.RetrofitClient;
import com.cn.miraclestar.constants.UrlConstant;
import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.service.http_service.UsersHttpService;
import com.cn.miraclestar.service.sql_service.UsersSqlService;
import com.cn.miraclestar.service.sql_service.callbacks.UsersCallback;
import com.cn.miraclestar.sql.entity.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersSqlServiceImpl implements UsersSqlService {
    private final Retrofit _retrofit = RetrofitClient.getClient(UrlConstant.baseurl);
    private final UsersHttpService _users_http_service = _retrofit.create(UsersHttpService.class);
    @Override
    public void getUser(String authorization,Long id, UsersCallback usersCallback) {

        Call<Result<Users>> call = _users_http_service.getUser(authorization,id);

        call.enqueue(new Callback<Result<Users>>() {
            @Override
            public void onResponse(Call<Result<Users>> call, Response<Result<Users>> response) {
                if(response.isSuccessful())
                    usersCallback.onSuccess(response.body());
                else
                    usersCallback.onFailure("Request failed: " + response.code());
            }
            @Override
            public void onFailure(Call<Result<Users>> call, Throwable t) {
                usersCallback.onFailure("Network error: "+t.getMessage());
            }
        });

    }
}
