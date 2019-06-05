package com.example.login;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.login.adapter.LevelChooseAdapter;
import com.example.login.entity.Level;
import com.example.login.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseLevelActivity extends AppCompatActivity {
    private List<Level> mLevelList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
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

        initLevels();

        mRecyclerView = findViewById(R.id.choose_level_recycler);
        LevelChooseAdapter mAdapter = new LevelChooseAdapter(this, mLevelList);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);
    }

    private void initLevels() {
        mLevelList = new ArrayList<>();
        String[] levelMapNameList = getResources().getStringArray(R.array.level_map_name);
        TypedArray levelMapImageIdList=getResources().obtainTypedArray(R.array.level_map_imageId);
        for(int i=0;i<levelMapNameList.length;i++){
            Level level = new Level();
            level.setName(levelMapNameList[i]);
            level.setImageId(levelMapImageIdList.getResourceId(i,R.drawable.demohua));
            mLevelList.add(level);
        }
    }
}
