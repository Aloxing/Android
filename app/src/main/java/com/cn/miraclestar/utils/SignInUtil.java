package com.cn.miraclestar.utils;

public class SignInUtil {
    public static boolean isTrueUsername(String username){
        if(username == null || username.isEmpty())return false;
        return username.length() > 2 && username.length() <= 16;
    }

    public static boolean isTruePassword(String password){
        if(password == null || password.isEmpty())return false;
        return password.length() >= 9 && password.length() <= 40;
    }
}
