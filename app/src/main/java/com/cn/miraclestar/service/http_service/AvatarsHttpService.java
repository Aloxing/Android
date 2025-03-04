package com.cn.miraclestar.service.http_service;

import com.cn.miraclestar.pojo.Result;
import com.cn.miraclestar.sql.entity.Avatars;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AvatarsHttpService {
    @GET("/avatars/{userId}")
    Call<Result<Avatars>> getAvatar(@Header("Authorization") String authorization, @Path("userId") Long userId);

    @Multipart
    @POST("/avatars/upload-image")
    Call<Result<String>> setAvatar(@Header("Authorization") String authorization, @Query("userId")Long userId, @Part MultipartBody.Part image);

}
