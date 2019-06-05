package com.mortal.hua.dao;

import com.mortal.hua.entity.LevelMap;
import org.apache.ibatis.annotations.Param;

public interface LevelMapDao {
    LevelMap selectInfoByName(String name);

    Integer selectIdByName(String name);

    int updateTopIdByName(@Param("name") String name, @Param("topId") Integer topId);
}
