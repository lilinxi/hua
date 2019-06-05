package com.mortal.hua.service;

import com.mortal.hua.entity.UserLevelMap;

import java.util.List;

public interface UserLevelMapService {
    boolean addOrUpdateUserRecord(UserLevelMap userLevelMap);

    UserLevelMap getUserRecordByUserNameAndLevelMapName(String userName, String levelMapName);

    List<UserLevelMap> getUserRecordsByUserName(String userName);

    void fill(UserLevelMap userLevelMap);
}
