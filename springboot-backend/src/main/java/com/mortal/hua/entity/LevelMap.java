package com.mortal.hua.entity;

import lombok.Data;

@Data
public class LevelMap {
    private Integer id;
    private String name;
    private String mapString;
    private String mapXYString;
    private String topUserName;
    private String topUserScore;
}
