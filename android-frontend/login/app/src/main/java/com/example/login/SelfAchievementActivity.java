package com.example.login;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.login.adapter.LevelAchievementAdapter;
import com.example.login.adapter.LevelChooseAdapter;
import com.example.login.entity.User;
import com.example.login.entity.UserLevelMap;
import com.example.login.local.Preference;
import com.example.login.util.ActivityUtil;
import com.example.login.util.CommonUtil;
import com.example.login.util.http.BaseAsyncHttpHandler;
import com.example.login.util.http.HttpUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelfAchievementActivity extends AppCompatActivity {
    private TextView mStarView;
    private TextView mScoreView;
    private RecyclerView mRecyclerView;
    private List<UserLevelMap> mUserLevelMapList;
    private LevelAchievementAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_achievement);
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

        mStarView = findViewById(R.id.self_star_view);
        mScoreView = findViewById(R.id.self_score_view);
        mRecyclerView = findViewById(R.id.self_achievement_recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        getSelfInfo();
        getSelfUserLevelMap();
    }

    private void getSelfInfo() {
        HashMap<String, String> params = new HashMap<>();
        String userName = Preference.getPreference(this, MainActivity.prefFile, getResources().getString(R.string.user_name));
        params.put(getResources().getString(R.string.user_name), userName);
        String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userServer_getUserInfo);

        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                Map map = responseResult.toMap();
                int star = (int)(double)map.get("stars");
                long score = (long) (double) map.get("scores");
                mStarView.setText(getResources().getString(R.string.self_star_beginText) + star);
                mScoreView.setText(getResources().getString(R.string.self_score_beginText) + score+getResources().getString(R.string.self_score_endText) );
            }
        });
    }

    private void getSelfUserLevelMap() {
        initUserLevelMaps();
        mRecyclerView = findViewById(R.id.self_achievement_recycler);
        mAdapter = new LevelAchievementAdapter(this, mUserLevelMapList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void initUserLevelMaps() {
        defaultInitUserLevelMaps();
        getSuccessUserLevelMap();
    }

    private void getSuccessUserLevelMap() {
        HashMap<String, String> params = new HashMap<>();
        String userName = Preference.getPreference(this, MainActivity.prefFile, getResources().getString(R.string.user_name));
        params.put(getResources().getString(R.string.user_level_map_userName), userName);
        String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userLevelMapServer_getUserRecordsByUserName);

        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                    List<UserLevelMap> successList = CommonUtil.getBean(responseResult.getData().toString(), new TypeToken<List<UserLevelMap>>(){}.getType());
                for (UserLevelMap successUserLevelMap : successList) {
                    for (int i = 0; i < mUserLevelMapList.size(); i++) {
                        UserLevelMap userLevelMap = mUserLevelMapList.get(i);
                        if (successUserLevelMap.getLevelMapName().equals(userLevelMap.getLevelMapName())) {
                            userLevelMap.setScore(successUserLevelMap.getScore());
                            mAdapter.notifyItemChanged(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void defaultInitUserLevelMaps() {
        mUserLevelMapList = new ArrayList<>();
        String[] levelMapNameList = getResources().getStringArray(R.array.level_map_name);
        TypedArray levelMapImageIdList=getResources().obtainTypedArray(R.array.level_map_imageId);
        for (int i = 0; i < levelMapNameList.length; i++) {
            UserLevelMap userLevelMap = new UserLevelMap();
            userLevelMap.setLevelMapName(levelMapNameList[i]);
            userLevelMap.setImageId(levelMapImageIdList.getResourceId(i, R.drawable.demohua));
            mUserLevelMapList.add(userLevelMap);
        }
    }
}
