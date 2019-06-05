package com.mortal.hua.dao;

import com.mortal.hua.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

    String selectNameByName(String name);

    User selectInfoByName(String name);

    Integer selectIdByName(String name);

    List<User> selectTopStars(Integer count);

    List<User> selectTopScores(Integer count);

    int insert(User user);
}
