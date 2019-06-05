package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.util.http.AsyncHttpHandler;
import com.example.login.util.http.HttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class UserController {
    public static boolean register(String name,String password) {
        return false;
    }

    public static User login(String name, String password) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        final User[] user = {null};
        HttpUtil.doPost(null, params, new AsyncHttpHandler() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                Gson gson = new Gson();
                user[0] = gson.fromJson(s, User.class);
            }
        });
        return user[0];
    }

    public static void logout(User user) {

    }

    public static List<User> getTop10Progress() {
        return null;
    }

    public static List<User> getTop10Score() {
        return null;
    }

}