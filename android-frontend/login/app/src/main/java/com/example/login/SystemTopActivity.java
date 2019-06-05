package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login.adapter.LevelChooseAdapter;
import com.example.login.adapter.SystemTopScoreAdapter;
import com.example.login.adapter.SystemTopStarAdapter;
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

public class SystemTopActivity extends AppCompatActivity {
    private int topCount = 5;
    private List<User> mStarUserList;
    private List<User> mScoreUserList;
    private RecyclerView mStarRecyclerView;
    private RecyclerView mScoreRecyclerView;
    private SystemTopStarAdapter mStarAdapter;
    private SystemTopScoreAdapter mScoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_top);
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

        initUserList();
        mStarRecyclerView = findViewById(R.id.system_top_star_recycler);
        mStarAdapter = new SystemTopStarAdapter(this, mStarUserList);
        mStarRecyclerView.setAdapter(mStarAdapter);
        mStarRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mScoreRecyclerView = findViewById(R.id.system_top_score_recycler);
        mScoreAdapter = new SystemTopScoreAdapter(this, mScoreUserList);
        mScoreRecyclerView.setAdapter(mScoreAdapter);
        mScoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initUserList() {
        defaultUserList();
        initStarUserList();
        initScoreUserList();
    }

    private void defaultUserList() {
        mStarUserList = new ArrayList<>();
        for (int i = 0; i < topCount; i++) {
            mStarUserList.add(new User());
        }
        mScoreUserList = new ArrayList<>();
        for (int i = 0; i < topCount; i++) {
            mScoreUserList.add(new User());
        }
    }

    private void initStarUserList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("count", topCount+"");
        String url= getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userServer_getTopStars);
        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                List<User> userList = CommonUtil.getBean(responseResult.getData().toString(), new TypeToken<List<User>>(){}.getType());
                if (userList != null) {
                    for (int i = 0; i < userList.size(); i++) {
                        mStarUserList.get(i).setName(userList.get(i).getName());
                        mStarUserList.get(i).setStars(userList.get(i).getStars());
                        mStarAdapter.notifyItemChanged(i);
                    }
                }
            }
        });
    }

    private void initScoreUserList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("count", topCount+"");
        String url= getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userServer_getTopScores);
        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                List<User> userList = CommonUtil.getBean(responseResult.getData().toString(), new TypeToken<List<User>>(){}.getType());
                if (userList != null) {
                    for (int i = 0; i < userList.size(); i++) {
                        mScoreUserList.get(i).setName(userList.get(i).getName());
                        mScoreUserList.get(i).setScores(userList.get(i).getScores());
                        mScoreAdapter.notifyItemChanged(i);
                    }
                }
            }
        });
    }


    public void showSystemTopAlertDialog(View view) {
        ActivityUtil.displayDialog(this, null, "成绩相同者按取得相应成绩的时间排序", null, null);
    }
}
