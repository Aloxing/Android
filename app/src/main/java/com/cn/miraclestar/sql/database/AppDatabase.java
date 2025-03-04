package com.cn.miraclestar.sql.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.cn.miraclestar.sql.dao.UsersDao;
import com.cn.miraclestar.sql.entity.Users;

@Database(entities = {Users.class},version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
}
