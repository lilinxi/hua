package com.example.login.util.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

public class HttpUtil {
    public static void doGet(String url, final AsyncHttpHandler handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                handler.onSuccess(s);
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
                handler.onFailure(throwable, s);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                handler.onFinish();
            }

            @Override
            public void onStart() {
                super.onStart();
                handler.onStart();
            }
        });
    }

    public static void doPost(String url, Map<String, String> params, final AsyncHttpHandler handler) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(url,new RequestParams(params), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                handler.onSuccess(s);
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
                handler.onFailure(throwable, s);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                handler.onFinish();
            }

            @Override
            public void onStart() {
                super.onStart();
                handler.onStart();
            }
        });
    }
}