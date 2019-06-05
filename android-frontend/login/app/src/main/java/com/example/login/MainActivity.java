package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.login.local.Preference;
import com.example.login.util.ActivityUtil;
import com.example.login.util.http.BaseAsyncHttpHandler;
import com.example.login.util.http.HttpUtil;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private final AppCompatActivity that = this;
    private EditText mEditTextName;
    private EditText mEditTextPassword;
    public static final String prefFile = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityUtil.fullScreenDisplay(this);

        mEditTextName = findViewById(R.id.input_name);
        mEditTextPassword = findViewById(R.id.input_password);

        String name = Preference.getPreference(this, prefFile, getResources().getString(R.string.user_name));
        String password = Preference.getPreference(this, prefFile, getResources().getString(R.string.user_password));
        if (name != null && !name.trim().equals("") && password != null && !password.trim().equals("")) {
            mEditTextName.setText(name);
            mEditTextPassword.setText(password);
        }
    }

    public void register(View view) {
        String name = mEditTextName.getText().toString();
        String password = mEditTextPassword.getText().toString();
        if (!name.trim().equals("") && !password.trim().equals("")) {
            HashMap<String, String> params = new HashMap<>();
            params.put(getResources().getString(R.string.user_name), name);
            params.put(getResources().getString(R.string.user_password), password);
            String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userServer_register);
            HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
                @Override
                public void onResponseResultSuccess() {
                    super.onResponseResultSuccess();
                    ActivityUtil.displayDebugToast(getApplicationContext(), getResources().getString(R.string.register_success_msg));
                }
            });
        } else {
            ActivityUtil.displayDebugToast(getApplicationContext(), getResources().getString(R.string.name_or_password_is_null_msg));
        }
    }

    public void login(View view) {
        String name = mEditTextName.getText().toString();
        String password = mEditTextPassword.getText().toString();
        if (!name.trim().equals("") && !password.trim().equals("")) {
            final HashMap<String, String> params = new HashMap<>();
            params.put(getResources().getString(R.string.user_name), name);
            params.put(getResources().getString(R.string.user_password), password);
            String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userServer_login);
            HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
                @Override
                public void onResponseResultSuccess() {
                    super.onResponseResultSuccess();
                    Preference.setPreference(that, prefFile, params);
                    Intent intent = new Intent(that, LobbyActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            ActivityUtil.displayDebugToast(getApplicationContext(), getResources().getString(R.string.name_or_password_is_null_msg));
        }
    }

    public static String getUserName(Context context) {
        return Preference.getPreference(context, MainActivity.prefFile, context.getResources().getString(R.string.user_name));
    }
}
