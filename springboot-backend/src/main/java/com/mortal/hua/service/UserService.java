package com.mortal.hua.service;


import com.mortal.hua.entity.User;

import java.util.List;

public interface UserService {
    User login(String name, String password);

    boolean register(User user);

    User getUserInfoByName(String name);

    List<User> getTopStars(Integer count);

    List<User> getTopScores(Integer count);
}
