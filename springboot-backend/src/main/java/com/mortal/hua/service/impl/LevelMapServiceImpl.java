package com.mortal.hua.service.impl;

import com.mortal.hua.dao.LevelMapDao;
import com.mortal.hua.entity.LevelMap;
import com.mortal.hua.service.LevelMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelMapServiceImpl implements LevelMapService {
    @Autowired
    LevelMapDao levelMapDao;

    @Override
    public LevelMap getLevelMapByName(String name) {
        if (name != null && !name.trim().equals("")) {
            LevelMap levelMap = levelMapDao.selectInfoByName(name);
            if (levelMap != null) {
                return levelMap;
            } else {
                throw new RuntimeException("地图名不存在");
            }
        } else {
            throw new RuntimeException("地图名为空");
        }
    }

    @Override
    public boolean updateTopIdByName(String name, Integer topId) {
        if (name != null && !name.trim().equals("")) {
            if (topId != null) {
                int ret = levelMapDao.updateTopIdByName(name, topId);
                if (ret > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新最高记录失败");
                }
            } else {
                throw new RuntimeException("记录 id 为空");
            }
        } else {
            throw new RuntimeException("地图名为空");
        }
    }
}
