package com.cn.miraclestar.service.sql_service;

import com.cn.miraclestar.service.sql_service.callbacks.AvatarsCallback;
import com.cn.miraclestar.sql.entity.Avatars;

import java.util.List;

import okhttp3.MultipartBody;

public interface AvatarsSqlService {
    void getAvatar(String authorization, Long userId, AvatarsCallback avatarsCallback);
    void setAvatar(String authorization, Long userId, MultipartBody.Part image, AvatarsCallback avatarsCallback);
}
