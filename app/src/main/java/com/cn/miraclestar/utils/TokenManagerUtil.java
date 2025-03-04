package com.cn.miraclestar.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManagerUtil {
    private static final String PREF_NAME = "TokenPref";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USERID = "user_id";
    private final SharedPreferences sharedPreferences;

    public TokenManagerUtil(Context context){
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public void saveUserId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, id);
        editor.apply();
    }

    // 从 SharedPreferences 获取 token
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USERID, null);
    }

    // 清除 token
    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_USERID);
        editor.apply();
    }
}
