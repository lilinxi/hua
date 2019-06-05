package com.example.login.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.entity.Level;
import com.example.login.entity.LevelMapImpl;
import com.example.login.entity.UserLevelMap;

import java.util.List;

public class LevelAchievementAdapter extends RecyclerView.Adapter<LevelAchievementAdapter.LevelAchievementViewHolder> {
    private List<UserLevelMap> mUserLevelMapList;
    private Context mContext;

    public LevelAchievementAdapter(Context mContext, List<UserLevelMap> mUserLevelMapList) {
        this.mUserLevelMapList = mUserLevelMapList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LevelAchievementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.achievement_level_item, viewGroup, false);
        return new LevelAchievementAdapter.LevelAchievementViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelAchievementViewHolder levelAchievementViewHolder, int i) {
        UserLevelMap userLevelMap = mUserLevelMapList.get(i);
        levelAchievementViewHolder.levelName.setText(userLevelMap.getLevelMapName());
        Glide.with(mContext).load(userLevelMap.getImageId()).into(levelAchievementViewHolder.levelImage);
        Long score = userLevelMap.getScore();
        if (score == null) {
            levelAchievementViewHolder.levelScore.setVisibility(View.INVISIBLE);
        } else {
            levelAchievementViewHolder.levelScore.setText(mContext.getResources().getString(R.string.achievement_level_score_beginText)+score+mContext.getResources().getString(R.string.achievement_level_score_endText));
        }
    }

    @Override
    public int getItemCount() {
        return mUserLevelMapList.size();
    }

    class LevelAchievementViewHolder extends RecyclerView.ViewHolder {
        ImageView levelImage;
        TextView levelName;
        TextView levelScore;

        public LevelAchievementViewHolder(@NonNull View itemView) {
            super(itemView);
            levelImage = itemView.findViewById(R.id.achievement_level_image);
            levelName = itemView.findViewById(R.id.achievement_level_name);
            levelScore = itemView.findViewById(R.id.achievement_level_score);
        }
    }
}
