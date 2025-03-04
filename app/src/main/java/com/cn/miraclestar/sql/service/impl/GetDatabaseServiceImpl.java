package com.cn.miraclestar.sql.service.impl;

import android.content.Context;

import androidx.room.Room;

import com.cn.miraclestar.sql.database.AppDatabase;
import com.cn.miraclestar.sql.service.GetDatabaseService;

public class GetDatabaseServiceImpl implements GetDatabaseService {
    @Override
    public AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "MiracleStar").build();
    }
}
