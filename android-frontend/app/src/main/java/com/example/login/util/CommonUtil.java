package com.example.login.util;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class CommonUtil {
    private static final Gson gson = new Gson();
    public static <T> T getBean(String jsonString,Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> T getBean(String jsonString, Type typeOfT) {
        return gson.fromJson(jsonString, typeOfT);
    }

    public static String getJson(Object object) {
        return gson.toJson(object);
    }

}
