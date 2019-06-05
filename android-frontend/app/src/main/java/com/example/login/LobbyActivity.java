package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.login.util.ActivityUtil;
import com.example.login.util.http.BaseAsyncHttpHandler;
import com.example.login.util.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        ActivityUtil.fullScreenDisplay(this);
        View view = findViewById(R.id.topbar_back_icon);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView userNameView = findViewById(R.id.topbar_user_name);
        userNameView.setText(MainActivity.getUserName(this));
    }

    public void gotoChooseLevel(View view) {
        Intent intent = new Intent(this, ChooseLevelActivity.class);
        startActivity(intent);
    }

    public void gotoSelfAchievement(View view) {
        Intent intent = new Intent(this, SelfAchievementActivity.class);
        startActivity(intent);
    }

    public void gotoSystemTop(View view) {
        Intent intent = new Intent(this, SystemTopActivity.class);
        startActivity(intent);
    }

    public void quickBegin(View view) {
        String[] levelMapNameList = getResources().getStringArray(R.array.level_map_name);
        final String levelMapName = levelMapNameList[new Random().nextInt(levelMapNameList.length)];
        HashMap<String, String> params = new HashMap<>();
        params.put(getResources().getString(R.string.map_name), levelMapName);
        String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.levelMapServer_getLevelMap);
        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                super.onResponseResultSuccess();
                Map<String, Object> data = responseResult.toMap();
                Intent intent = new Intent(context, PlayingActivity.class);
                intent.putExtra(getResources().getString(R.string.level_name), levelMapName);
                intent.putExtra(getResources().getString(R.string.level_mapString), data.get("mapString").toString());
                intent.putExtra(getResources().getString(R.string.level_mapXYString), data.get("mapXYString").toString());
                startActivity(intent);
            }
        });
    }
}

