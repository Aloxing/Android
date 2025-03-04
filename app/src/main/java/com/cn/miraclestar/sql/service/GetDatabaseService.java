package com.cn.miraclestar.sql.service;

import android.content.Context;

import com.cn.miraclestar.sql.database.AppDatabase;

public interface GetDatabaseService {
    AppDatabase getDatabase(Context context);
}
