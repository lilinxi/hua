package com.mortal.hua.dao;

import com.mortal.hua.entity.UserLevelMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLevelMapDao {
    int insertOrUpdate(UserLevelMap userLevelMap);

    UserLevelMap selectInfoByUserNameAndLevelMapName(@Param("userName") String userName, @Param("levelMapName") String levelMapName);

    List<UserLevelMap> selectInfoByUserName(String userName);
}
