package com.cn.miraclestar.service.sql_service;

import com.cn.miraclestar.service.sql_service.callbacks.UsersCallback;
import com.cn.miraclestar.sql.entity.Users;

public interface UsersSqlService {
    void getUser(String authorization,Long id, UsersCallback usersCallback);
}
