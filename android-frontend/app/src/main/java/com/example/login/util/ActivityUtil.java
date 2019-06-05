package com.example.login.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.PlayingActivity;
import com.example.login.R;

public class ActivityUtil {
    public static void displayDebugToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void fullScreenDisplay(AppCompatActivity activity) {
        // 得到当前界面的装饰视图
        View decorView = activity.getWindow().getDecorView();
        // SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
        // 设置系统UI元素的可见性
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // 隐藏标题栏
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.hide();
    }

    public static void displayDialog(final Context context, String title, String message, View view, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(context);
        if (title != null) {
            myAlertBuilder.setTitle(title);
        }
        if (message != null) {
            myAlertBuilder.setMessage(message);
        }
        if (view != null) {
            myAlertBuilder.setView(view);
        }
        myAlertBuilder.setOnCancelListener(onCancelListener);
        myAlertBuilder.show();
    }
}
