package com.example.login;

import com.example.login.entity.LevelMap;
import com.example.login.entity.LevelMapImpl;
import com.example.login.entity.Role;
import com.example.login.util.CommonUtil;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class LevelMapImplTest {
    @Test
    public void testCharToInt() {
        assertEquals(1, Integer.parseInt(String.valueOf("1".charAt(0)), 16));
        assertEquals(10, Integer.parseInt(String.valueOf("a".charAt(0)), 16));
        assertEquals(11, Integer.parseInt(String.valueOf("b".charAt(0)), 16));
    }

    @Test
    public void testLevelMapInit() {
        LevelMap levelMap = new LevelMapImpl("横刀立马", LevelMapImpl.DefaultMapString, LevelMapImpl.DefaultMapXYString);
    }

    @Test
    public void testLevelMapGet() {
        LevelMap levelMap = new LevelMapImpl("横刀立马", LevelMapImpl.DefaultMapString, LevelMapImpl.DefaultMapXYString);
        assertEquals(R.id.caocao, (int) levelMap.getUp(R.id.guanyu));
        assertEquals(R.id.bing2, (int) levelMap.getDown(R.id.guanyu));
        assertEquals(R.id.huangzhong, (int) levelMap.getLeft(R.id.guanyu));
        assertEquals(R.id.zhangfei, (int) levelMap.getRight(R.id.guanyu));
    }

    @Test
    public void testLevelMapCan() {
        LevelMap levelMap = new LevelMapImpl("横刀立马", LevelMapImpl.DefaultMapString, LevelMapImpl.DefaultMapXYString);
        assertEquals(false, levelMap.canUp(R.id.bing2));
        assertEquals(true, levelMap.canDown(R.id.bing2));
        assertEquals(false, levelMap.canLeft(R.id.bing2));
        assertEquals(false, levelMap.canRight(R.id.bing2));
    }

    @Test
    public void testLevelMapGo() {
        LevelMap levelMap = new LevelMapImpl("横刀立马", LevelMapImpl.DefaultMapString, LevelMapImpl.DefaultMapXYString);
        levelMap.goDown(R.id.bing2);
        assertEquals(R.id.kong1, (int) levelMap.getUp(R.id.bing2));
        assertEquals(null, levelMap.getDown(R.id.bing2));
        assertEquals(R.id.bing1, (int) levelMap.getLeft(R.id.bing2));
        assertEquals(R.id.kong2, (int) levelMap.getRight(R.id.bing2));

        assertEquals(R.id.guanyu, (int) levelMap.getUp(R.id.kong1));
        assertEquals(R.id.bing2, (int) levelMap.getDown(R.id.kong1));
        assertEquals(R.id.huangzhong, (int) levelMap.getLeft(R.id.kong1));
        assertEquals(R.id.bing3, (int) levelMap.getRight(R.id.kong1));
    }

    @Test
    public void testLevelMapHengDaoLiMa() {
        LevelMap levelMap = new LevelMapImpl("横刀立马", LevelMapImpl.DefaultMapString, LevelMapImpl.DefaultMapXYString);

        levelMap.goRight(R.id.bing1);
        levelMap.goLeft(R.id.bing4);
        levelMap.goDown(R.id.huangzhong);
        levelMap.goDown(R.id.zhangfei);
        levelMap.goRight(R.id.guanyu);
        levelMap.goUp(R.id.bing2);
        levelMap.goLeft(R.id.bing2);
        levelMap.goUp(R.id.bing1);
        levelMap.goUp(R.id.bing1);

        levelMap.goRight(R.id.huangzhong);
        levelMap.goDown(R.id.bing2);
        levelMap.goDown(R.id.bing2);
        levelMap.goLeft(R.id.bing1);
        levelMap.goDown(R.id.bing1);
        levelMap.goLeft(R.id.guanyu);
        levelMap.goLeft(R.id.guanyu);
        levelMap.goUp(R.id.bing3);
        levelMap.goRight(R.id.bing3);
        levelMap.goUp(R.id.bing4);
        levelMap.goUp(R.id.bing4);
        levelMap.goRight(R.id.huangzhong);

        levelMap.goRight(R.id.bing1);
        levelMap.goDown(R.id.bing1);
        levelMap.goDown(R.id.guanyu);
        levelMap.goLeft(R.id.bing4);
        levelMap.goLeft(R.id.bing4);
        levelMap.goLeft(R.id.bing3);
        levelMap.goLeft(R.id.bing3);
        levelMap.goUp(R.id.huangzhong);
        levelMap.goUp(R.id.zhangfei);
        levelMap.goRight(R.id.bing1);
        levelMap.goRight(R.id.bing1);
        levelMap.goRight(R.id.bing2);
        levelMap.goRight(R.id.bing2);

        levelMap.goDown(R.id.guanyu);
        levelMap.goDown(R.id.bing3);
        levelMap.goLeft(R.id.bing3);
        levelMap.goLeft(R.id.huangzhong);
        levelMap.goLeft(R.id.zhangfei);
        levelMap.goDown(R.id.zhaoyun);
        levelMap.goDown(R.id.zhaoyun);

        levelMap.goRight(R.id.caocao);
        levelMap.goRight(R.id.machao);
        levelMap.goUp(R.id.bing4);
        levelMap.goUp(R.id.bing4);
        levelMap.goUp(R.id.bing3);
        levelMap.goUp(R.id.bing3);
        levelMap.goLeft(R.id.huangzhong);
        levelMap.goDown(R.id.machao);
        levelMap.goDown(R.id.machao);
        levelMap.goLeft(R.id.caocao);

        levelMap.goUp(R.id.zhaoyun);
        levelMap.goUp(R.id.zhaoyun);
        levelMap.goRight(R.id.zhangfei);
        levelMap.goUp(R.id.bing2);
        levelMap.goUp(R.id.bing2);
        levelMap.goLeft(R.id.bing1);
        levelMap.goUp(R.id.bing1);
        levelMap.goRight(R.id.guanyu);
        levelMap.goRight(R.id.guanyu);

        levelMap.goDown(R.id.huangzhong);
        levelMap.goDown(R.id.machao);
        levelMap.goLeft(R.id.bing2);
        levelMap.goLeft(R.id.bing2);
        levelMap.goDown(R.id.caocao);
        levelMap.goRight(R.id.bing4);
        levelMap.goRight(R.id.bing4);
        levelMap.goUp(R.id.bing3);
        levelMap.goRight(R.id.bing3);
        levelMap.goUp(R.id.bing2);
        levelMap.goUp(R.id.bing2);
        levelMap.goUp(R.id.huangzhong);
        levelMap.goUp(R.id.huangzhong);
        levelMap.goLeft(R.id.machao);
        levelMap.goLeft(R.id.bing1);
        levelMap.goDown(R.id.bing1);
        levelMap.goDown(R.id.caocao);

        levelMap.goDown(R.id.bing4);
        levelMap.goLeft(R.id.bing4);
        levelMap.goLeft(R.id.zhaoyun);
        levelMap.goUp(R.id.zhangfei);
        levelMap.goUp(R.id.zhangfei);
        levelMap.goRight(R.id.caocao);

        levelMap.goDown(R.id.bing4);
        levelMap.goDown(R.id.bing4);
        levelMap.goDown(R.id.bing3);
        levelMap.goRight(R.id.bing2);
        levelMap.goUp(R.id.huangzhong);

        levelMap.goUp(R.id.machao);
        levelMap.goLeft(R.id.bing1);
        levelMap.goDown(R.id.bing4);
        levelMap.goLeft(R.id.caocao);

        levelMap.goDown(R.id.zhangfei);
        levelMap.goDown(R.id.zhangfei);
        levelMap.goRight(R.id.zhaoyun);
        levelMap.goRight(R.id.bing2);
        levelMap.goRight(R.id.bing3);
        levelMap.goRight(R.id.huangzhong);
        levelMap.goUp(R.id.machao);
        levelMap.goUp(R.id.machao);
        levelMap.goLeft(R.id.caocao);
        levelMap.goDown(R.id.bing3);
        levelMap.goDown(R.id.bing3);
        levelMap.goDown(R.id.bing2);
        levelMap.goDown(R.id.bing2);
        levelMap.goLeft(R.id.zhaoyun);
        levelMap.goUp(R.id.zhangfei);
        levelMap.goUp(R.id.zhangfei);
        levelMap.goRight(R.id.bing3);
        levelMap.goUp(R.id.bing3);

        levelMap.goUp(R.id.guanyu);
        levelMap.goRight(R.id.bing4);
        levelMap.goRight(R.id.bing4);
        levelMap.goRight(R.id.bing1);
        levelMap.goRight(R.id.bing1);
        levelMap.goDown(R.id.caocao);
        levelMap.goLeft(R.id.bing2);
        levelMap.goLeft(R.id.bing2);
        levelMap.goLeft(R.id.bing3);
        levelMap.goLeft(R.id.bing3);
        levelMap.goUp(R.id.guanyu);
        levelMap.goUp(R.id.bing1);
        levelMap.goRight(R.id.bing1);
        levelMap.goRight(R.id.caocao);

        assertEquals(true, levelMap.success());
    }
}