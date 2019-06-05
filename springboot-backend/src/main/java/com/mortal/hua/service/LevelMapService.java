package com.mortal.hua.service;

import com.mortal.hua.entity.LevelMap;

public interface LevelMapService {
    LevelMap getLevelMapByName(String name);

    boolean updateTopIdByName(String name, Integer topId);
}
