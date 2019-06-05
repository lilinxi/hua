package com.example.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.entity.LevelMap;
import com.example.login.entity.LevelMapImpl;
import com.example.login.entity.Role;
import com.example.login.local.Preference;
import com.example.login.util.ActivityUtil;
import com.example.login.util.CommonUtil;
import com.example.login.util.http.AsyncHttpHandler;
import com.example.login.util.http.BaseAsyncHttpHandler;
import com.example.login.util.http.HttpUtil;
import com.example.login.util.http.ResponseResult;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PlayingActivity extends AppCompatActivity {
    private TextView mLevelNameTextView;
    private TextView mLevelScoreTextView;
    private TextView mLevelTimerTextView;
    private String levelMapName;
    private LevelMap levelMap;
    private long baseTime = 0;
    private int stepCount = 0;
    private Float x = null;
    private Float y = null;
    private Integer moveId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
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

        Intent intent = getIntent();
        levelMapName = intent.getStringExtra(getResources().getString(R.string.level_name));
        String mapString = intent.getStringExtra(getResources().getString(R.string.level_mapString));
        String mapXYString = intent.getStringExtra(getResources().getString(R.string.level_mapXYString));
        levelMap = new LevelMapImpl(levelMapName, mapString, mapXYString);

        mLevelNameTextView = findViewById(R.id.level_name_label);
        mLevelNameTextView.setText(levelMapName);
        mLevelScoreTextView = findViewById(R.id.level_score_label);
        mLevelScoreTextView.setText(String.valueOf(stepCount));
        for (final Role role : Role.getRoles()) {
            TextView textView = findViewById(role.getId());
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            moveId = role.getId();
                            x = event.getX();
                            y = event.getY();
//                            mLevelScoreTextView.setText("is move");
                            break;
                        case MotionEvent.ACTION_UP:
                            if (x != null && y != null && moveId != null) {
//                                mLevelScoreTextView.setText("no move");
                                float x2 = event.getX();
                                float y2 = event.getY();
                                if ((x2 - x) > Math.abs(y2 - y)) {
//                                    mLevelNameTextView.setText("you");
                                    levelMap.goRight(moveId);
                                    resetConstraintSet(levelMap);
                                    stepIncrement();
                                } else if ((x - x2) > Math.abs(y2 - y)) {
//                                    mLevelNameTextView.setText("zuo");
                                    levelMap.goLeft(moveId);
                                    resetConstraintSet(levelMap);
                                    stepIncrement();
                                } else if ((y - y2) > Math.abs(x - x2)) {
//                                    mLevelNameTextView.setText("shang");
                                    levelMap.goUp(moveId);
                                    resetConstraintSet(levelMap);
                                    stepIncrement();
                                } else if ((y2 - y) > Math.abs(x - x2)) {
//                                    mLevelNameTextView.setText("xia");
                                    levelMap.goDown(moveId);
                                    resetConstraintSet(levelMap);
                                    stepIncrement();
                                }
                                x = null;
                                y = null;
                                moveId = null;
                            }
                            break;
                    }
                    return true;
                }
            });
        }
        resetConstraintSet(levelMap);

        baseTime = SystemClock.elapsedRealtime();
        mLevelTimerTextView = findViewById(R.id.level_timer);
        final Handler startTimerHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                mLevelTimerTextView.setText((String) msg.obj);
            }
        };
        new Timer("开机计时器").scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int time = (int) ((SystemClock.elapsedRealtime() - PlayingActivity.this.baseTime) / 1000);
                String hh = new DecimalFormat("00").format(time / 3600);
                String mm = new DecimalFormat("00").format(time % 3600 / 60);
                String ss = new DecimalFormat("00").format(time % 60);
                String timeFormat = hh + ":" + mm + ":" + ss;
                Message msg = new Message();
                msg.obj = timeFormat;
                startTimerHandler.sendMessage(msg);
            }
        }, 0, 1000L);
    }

    private void clearConstraint(ConstraintSet set, int id) {
        set.clear(id, ConstraintSet.BOTTOM);
        set.clear(id, ConstraintSet.TOP);
        set.clear(id, ConstraintSet.START);
        set.clear(id, ConstraintSet.END);
    }

    private void resetConstraintSet(LevelMap levelMap) {
        ConstraintLayout cl = findViewById(R.id.playing_map);
        ConstraintSet set = new ConstraintSet();
        set.clone(cl);
        for (Role role : Role.getRoles()) {
            int id = role.getId();
            clearConstraint(set, id);
            Integer l = levelMap.getLeft(id);
            if (l == null) {
                set.connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            } else {
                set.connect(id, ConstraintSet.START, l, ConstraintSet.END);
            }
            Integer r = levelMap.getRight(id);
            if (r == null) {
                set.connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            } else {
                set.connect(id, ConstraintSet.END, r, ConstraintSet.START);
            }
            Integer t = levelMap.getUp(id);
            if (t == null) {
                set.connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            } else {
                set.connect(id, ConstraintSet.TOP, t, ConstraintSet.BOTTOM);
            }
            Integer b = levelMap.getDown(id);
            if (b == null) {
                set.connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            } else {
                set.connect(id, ConstraintSet.BOTTOM, b, ConstraintSet.TOP);
            }
        }
        set.applyTo(cl);
    }

    private void stepIncrement() {
        stepCount++;
        mLevelScoreTextView.setText(String.valueOf(stepCount));
        if (levelMap.success()) {
            success();
        }
    }

    private void success() {
        HashMap<String, String> params = new HashMap<>();
        String userName = Preference.getPreference(this, MainActivity.prefFile, getResources().getString(R.string.user_name));
        params.put(getResources().getString(R.string.user_level_map_userName), userName);
        params.put(getResources().getString(R.string.user_level_map_levelMapName), levelMapName);
        params.put(getResources().getString(R.string.user_level_map_stepCount), String.valueOf(stepCount));
        params.put(getResources().getString(R.string.user_level_map_timeMs), String.valueOf(SystemClock.elapsedRealtime() - baseTime));
        String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userLevelMapServer_addOrUpdateUserRecord);

        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this) {
            @Override
            public void onResponseResultSuccess() {
                super.onResponseResultSuccess();
                if (Boolean.parseBoolean(responseResult.getData().toString())) {
                    // 新纪录
                    getResult();
                } else {
                    displayResult(false, responseResult.toMap());
                }
            }
        });
    }

    private void getResult() {
        HashMap<String, String> params = new HashMap<>();
        String userName = Preference.getPreference(this, MainActivity.prefFile, getResources().getString(R.string.user_name));
        params.put(getResources().getString(R.string.user_level_map_userName), userName);
        params.put(getResources().getString(R.string.user_level_map_levelMapName), levelMapName);
        String url = getResources().getString(R.string.userServerAddress) + getResources().getString(R.string.userLevelMapServer_getUserRecordByUserNameAndLevelMapName);
        HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(this){
            @Override
            public void onResponseResultSuccess() {
                super.onResponseResultSuccess();


                responseResult.getData();
                displayResult(true, responseResult.toMap());
            }
        });
    }

    private void displayResult(boolean newRecord, Map map) {
        long score = (long)(double)map.get("score");
        int star = (int)(double)map.get("star");
        String title = null;
        if (newRecord) {
            title = "新纪录！";
        }
        View view = View.inflate(PlayingActivity.this, R.layout.new_record_dialog, null);
        TextView mTextView = view.findViewById(R.id.new_record_score);
        mTextView.setText("分数：" + score);
        TypedArray starIds = getResources().obtainTypedArray(R.array.new_record_stars);
        for (int i = 0; i < star; i++) {
            TextView mStarIcon = view.findViewById(starIds.getResourceId(i, 0));
            if (mStarIcon != null) {
                mStarIcon.setBackground(getResources().getDrawable(R.drawable.ic_star));
            }
        }
        TextView mAlertIcon = view.findViewById(R.id.new_record_alert);
        mAlertIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.displayDialog(PlayingActivity.this, null, "通关：一颗星\n\n五分钟内通关：一颗星\n\n100 步内通关：一颗星", null,null);
            }
        });
        ActivityUtil.displayDialog(PlayingActivity.this, title, null, view, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(PlayingActivity.this, ChooseLevelActivity.class);
                startActivity(intent);
            }
        });
    }
}
