package com.example.login.util.http;

import android.content.Context;

import com.example.login.util.ActivityUtil;
import com.example.login.util.CommonUtil;

public class BaseAsyncHttpHandler extends AsyncHttpHandler {
    protected Context context;
    protected ResponseResult responseResult;

    public BaseAsyncHttpHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess(String s) {
        super.onSuccess(s);
        responseResult = CommonUtil.getBean(s, ResponseResult.class);
        if (responseResult.isSuccess()) {
            onResponseResultSuccess();
        } else {
            ActivityUtil.displayDebugToast(context, responseResult.getMsg());
        }
    }

    public void onResponseResultSuccess() {
    }
}
