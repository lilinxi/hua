package com.example.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login.PlayingActivity;
import com.example.login.R;
import com.example.login.entity.Level;
import com.example.login.entity.LevelMapImpl;
import com.example.login.util.ActivityUtil;
import com.example.login.util.CommonUtil;
import com.example.login.util.http.AsyncHttpHandler;
import com.example.login.util.http.BaseAsyncHttpHandler;
import com.example.login.util.http.HttpUtil;
import com.example.login.util.http.ResponseResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelChooseAdapter extends RecyclerView.Adapter<LevelChooseAdapter.LevelChooseViewHolder> {
    private List<Level> mLevelList;
    private Context mContext;

    public LevelChooseAdapter(Context context, List<Level> mLevelList) {
        this.mLevelList = mLevelList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LevelChooseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.choose_level_item, viewGroup, false);
        return new LevelChooseViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelChooseViewHolder viewHolder, int i) {
        Level level = mLevelList.get(i);
        viewHolder.levelName.setText(level.getName());
        Glide.with(mContext).load(level.getImageId()).into(viewHolder.levelImage);
    }

    @Override
    public int getItemCount() {
        return mLevelList.size();
    }

    class LevelChooseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView levelImage;
        TextView levelName;
        Button levelBeginButton;

        public LevelChooseViewHolder(@NonNull View itemView) {
            super(itemView);
            levelImage = itemView.findViewById(R.id.choose_level_image);
            levelName = itemView.findViewById(R.id.choose_level_name);
            levelBeginButton = itemView.findViewById(R.id.choose_level_begin_button);
            levelBeginButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(mContext, PlayingActivity.class);
            int mPosition = getLayoutPosition();
            final Level level = mLevelList.get(mPosition);

            HashMap<String, String> params = new HashMap<>();
            params.put(mContext.getResources().getString(R.string.map_name), level.getName());
            String url = mContext.getResources().getString(R.string.userServerAddress) + mContext.getResources().getString(R.string.levelMapServer_getLevelMap);
            HttpUtil.doPost(url, params, new BaseAsyncHttpHandler(mContext) {
                @Override
                public void onResponseResultSuccess() {
                    super.onResponseResultSuccess();
                    Map<String, Object> data = responseResult.toMap();

                    level.setMapString(data.get("mapString").toString());
                    level.setMapXYString(data.get("mapXYString").toString());

                    Intent intent = new Intent(mContext, PlayingActivity.class);
                    intent.putExtra(mContext.getResources().getString(R.string.level_name), level.getName());
                    intent.putExtra(mContext.getResources().getString(R.string.level_mapString), level.getMapString());
                    intent.putExtra(mContext.getResources().getString(R.string.level_mapXYString), level.getMapXYString());
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
