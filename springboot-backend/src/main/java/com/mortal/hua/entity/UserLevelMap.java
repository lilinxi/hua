package com.mortal.hua.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserLevelMap {
    private Integer id;
    private Integer userId;
    private Integer levelMapId;
    private Long timeMs;// <300*1000
    private Long stepCount;// <100
    private Long score;// >0
    private Integer star;
    private Long datetime;

    private String userName;
    private String levelMapName;

    public boolean isHigher(UserLevelMap userLevelMap) {
        if (userLevelMap == null) {
            return true;
        }else {
            if (score != null && userLevelMap.score != null) {
                return score > userLevelMap.score;
            } else {
                throw new RuntimeException("score 为空");
            }
        }
    }
}
