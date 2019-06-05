package com.example.login.entity;

import lombok.Data;

@Data
public class User {
    public int id;
    public String name;
    public String password;

    private Integer progress;
    private Integer stars;
    private Integer scores;
}
