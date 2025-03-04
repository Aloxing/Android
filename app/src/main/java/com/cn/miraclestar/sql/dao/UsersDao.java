package com.cn.miraclestar.sql.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cn.miraclestar.sql.entity.Users;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(Users... users);
    @Query("select * from users where user_id = :id")
    Single<Users> selectUser(Long id);
}
