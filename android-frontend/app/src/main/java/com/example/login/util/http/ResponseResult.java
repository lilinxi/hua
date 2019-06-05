package com.example.login.util.http;

import com.example.login.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ResponseResult {
    /**
     * 请求状态
     */
    private boolean success;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ResponseResult(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseResult(boolean success) {
        this.success = success;
    }

    public Map toMap() {
        return CommonUtil.getBean(this.data.toString(), Map.class);
    }
}

