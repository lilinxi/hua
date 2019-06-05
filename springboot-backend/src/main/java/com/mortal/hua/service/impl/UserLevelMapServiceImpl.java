package com.mortal.hua.service.impl;

import com.mortal.hua.dao.LevelMapDao;
import com.mortal.hua.dao.UserDao;
import com.mortal.hua.dao.UserLevelMapDao;
import com.mortal.hua.entity.UserLevelMap;
import com.mortal.hua.service.UserLevelMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLevelMapServiceImpl implements UserLevelMapService {
    @Autowired
    UserLevelMapDao userLevelMapDao;
    @Autowired
    UserDao userDao;
    @Autowired
    LevelMapDao levelMapDao;

    @Override
    public boolean addOrUpdateUserRecord(UserLevelMap userLevelMap) {
        String userName = userLevelMap.getUserName();
        if (userName != null && !userName.trim().equals("")) {
            int id = userDao.selectIdByName(userName);
            userLevelMap.setUserId(id);
        } else {
            throw new RuntimeException("用户名为空");
        }
        String levelMapName = userLevelMap.getLevelMapName();
        if (levelMapName != null && !levelMapName.trim().equals("")) {
            int id = levelMapDao.selectIdByName(levelMapName);
            userLevelMap.setLevelMapId(id);
        }else {
            throw new RuntimeException("地图名为空");
        }

        if (userLevelMap.getUserId() != null && userLevelMap.getLevelMapId() != null) {
            if (userLevelMap.getTimeMs() != null && userLevelMap.getStepCount() != null&&userLevelMap.getScore()!=null&&userLevelMap.getStar()!=null) {
                int ret = userLevelMapDao.insertOrUpdate(userLevelMap);
                if (ret > 0) {
                    return true;
                } else {
                    throw new RuntimeException("写入记录失败");
                }
            } else {
                throw new RuntimeException("记录内容初始化失败");
            }
        } else {
            throw new RuntimeException("记录关联 id 初始化失败");
        }
    }

    @Override
    public UserLevelMap getUserRecordByUserNameAndLevelMapName(String userName, String levelMapName) {
        if (userName != null && !userName.trim().equals("")) {
            if (levelMapName != null && !levelMapName.trim().equals("")) {
                return userLevelMapDao.selectInfoByUserNameAndLevelMapName(userName, levelMapName);
            } else {
                throw new RuntimeException("地图名为空");
            }
        } else {
            throw new RuntimeException("用户名为空");
        }
    }

    @Override
    public List<UserLevelMap> getUserRecordsByUserName(String userName) {
        if (userName != null && !userName.trim().equals("")) {
            return userLevelMapDao.selectInfoByUserName(userName);
        } else {
            throw new RuntimeException("用户名为空");
        }
    }

    public void fill(UserLevelMap userLevelMap) {
        long score = computeScore(userLevelMap.getTimeMs(), userLevelMap.getStepCount());
        userLevelMap.setScore(score);
        int star = computeStar(userLevelMap.getTimeMs(), userLevelMap.getStepCount());
        userLevelMap.setStar(star);
    }

    private long computeScore(long timeMs, long stepCount) {
        return 1000000 - timeMs - stepCount * 10;
    }

    private int computeStar(long timeMs, long stepCount) {
        int star = 1;
        if (timeMs < 300 * 1000) {
            star++;
        }
        if (stepCount < 100) {
            star++;
        }
        return star;
    }
}
