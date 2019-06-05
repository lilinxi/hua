package com.mortal.hua.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;

    private Integer progress;
    private Integer stars;
    private Integer scores;
}
