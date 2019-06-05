package com.example.login.entity;

public interface LevelMap {
    Integer getLeft(int id);

    Integer getRight(int id);

    Integer getUp(int id);

    Integer getDown(int id);

    boolean canLeft(int id);

    boolean canRight(int id);

    boolean canUp(int id);

    boolean canDown(int id);

    void goLeft(int id);

    void goRight(int id);

    void goUp(int id);

    void goDown(int id);

    boolean success();

    void undo();

    void redo();
}
