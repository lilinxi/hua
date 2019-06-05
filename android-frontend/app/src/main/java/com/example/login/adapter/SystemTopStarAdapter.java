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

public class SystemTopStarAdapter extends RecyclerView.Adapter<SystemTopStarAdapter.SystemTopStarViewHolder> {
    private List<User> mUserList;
    private Context mContext;
    private static int[] rankSize = new int[]{40, 30, 30, 25, 25, 20};

    public SystemTopStarAdapter(Context mContext, List<User> mUserList) {
        this.mUserList = mUserList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SystemTopStarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.system_top_star_item, viewGroup, false);
        return new SystemTopStarAdapter.SystemTopStarViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemTopStarViewHolder systemTopStarViewHolder, int i) {
        User user = mUserList.get(i);
        systemTopStarViewHolder.rankView.setText((i+1) + "");
        if (user.getName() != null&&user.getStars()!=null) {
            systemTopStarViewHolder.userNameView.setText(user.getName() + " ");
            systemTopStarViewHolder.starView.setText(user.getStars()+"");
            systemTopStarViewHolder.starIconView.setVisibility(View.VISIBLE);
        }else {
            systemTopStarViewHolder.userNameView.setText("暂无人上榜");
            systemTopStarViewHolder.starView.setText("");
            systemTopStarViewHolder.starIconView.setVisibility(View.INVISIBLE);
        }
        switch (i) {
            case 0:
                systemTopStarViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_1_color));
                systemTopStarViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[0]);
                systemTopStarViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[1]);
                systemTopStarViewHolder.starView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[1]);
                break;
            case 1:
                systemTopStarViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_2_color));
                systemTopStarViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[2]);
                systemTopStarViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[3]);
                systemTopStarViewHolder.starView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[3]);
                break;
            case 2:
                systemTopStarViewHolder.rankView.setTextColor(mContext.getResources().getColor(R.color.rank_3_color));
                systemTopStarViewHolder.rankView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[4]);
                systemTopStarViewHolder.userNameView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[5]);
                systemTopStarViewHolder.starView.setTextSize(TypedValue.COMPLEX_UNIT_SP,rankSize[5]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class SystemTopStarViewHolder extends RecyclerView.ViewHolder {
        TextView rankView;
        TextView userNameView;
        TextView starView;
        TextView starIconView;

        public SystemTopStarViewHolder(@NonNull View itemView) {
            super(itemView);
            rankView = itemView.findViewById(R.id.system_top_star_rank);
            userNameView = itemView.findViewById(R.id.system_top_star_userName);
            starView = itemView.findViewById(R.id.system_top_star_star);
            starIconView = itemView.findViewById(R.id.system_top_star_starIcon);
        }
    }
}
