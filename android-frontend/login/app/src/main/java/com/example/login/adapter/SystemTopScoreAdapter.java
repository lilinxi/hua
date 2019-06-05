package com.example.login.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.login.R;
import com.example.login.entity.User;
import com.example.login.entity.UserLevelMap;

import java.util.List;

public class SystemTopScoreAdapter extends RecyclerView.Adapter<SystemTopScoreAdapter.SystemTopScoreViewHolder> {
    private List<User> mUserList;
    private Context mContext;
    private static int[] rankSize = new int[]{40, 30, 30, 25, 25, 20};

    public SystemTopScoreAdapter(Context mContext, List<User> mUserList) {
        this.mUserList = mUserList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SystemTopScoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.system_top_score_item, viewGroup, false);
        return new SystemTopScoreAdapter.SystemTopScoreViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemTopScoreViewHolder systemTopScoreViewHolder, int i) {
        User user = mUserList.get(i);
        systemTopScoreViewHolder.rankView.setText((i+1) + "");
        if (user.getName() != null&&user.getScores()!=null) {
            systemTopScoreViewHolder.userNameView.setText(user.getName() + " ");
            systemTopScoreViewHolder.scoreView.setText(user.getScores()+"");
            systemTopScoreViewHolder.scoreIconView.setVisibility(View.VISIBLE);
        }else {
            systemTopScoreViewHolder.userNameView.setText("暂无人上榜");
            systemTopScoreViewHolder.scoreView.setText("");
            systemTopScoreViewHolder.scoreIconView.setVisibility(View.INVISIBLE);
        }
        switch (i) {
            case 0:
                systemTopScoreViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_1_color));
                systemTopScoreViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[0]);
                systemTopScoreViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[1]);
                systemTopScoreViewHolder.scoreView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[1]);
                break;
            case 1:
                systemTopScoreViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_2_color));
                systemTopScoreViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[2]);
                systemTopScoreViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[3]);
                systemTopScoreViewHolder.scoreView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[3]);
                break;
            case 2:
                systemTopScoreViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_3_color));
                systemTopScoreViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[4]);
                systemTopScoreViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[5]);
                systemTopScoreViewHolder.scoreView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[5]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class SystemTopScoreViewHolder extends RecyclerView.ViewHolder {
        TextView rankView;
        TextView userNameView;
        TextView scoreView;
        TextView scoreIconView;

        public SystemTopScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            rankView = itemView.findViewById(R.id.system_top_sore_rank);
            userNameView = itemView.findViewById(R.id.system_top_score_userName);
            scoreView = itemView.findViewById(R.id.system_top_score_score);
            scoreIconView = itemView.findViewById(R.id.system_top_score_scoreIcon);
        }
    }
}
