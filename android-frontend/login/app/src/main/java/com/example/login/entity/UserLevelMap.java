package com.example.login.entity;

import lombok.Data;

@Data
public class UserLevelMap {
    private String userName;
    private String levelMapName;
    private Long timeMs;
    private Long stepCount;
    private Long score;
    private Integer star;
    private Long datetime;

    private int imageId;
}
