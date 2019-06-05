package com.mortal.hua.service.impl;

import com.mortal.hua.dao.UserDao;
import com.mortal.hua.entity.User;
import com.mortal.hua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String name, String password) {
        if (name != null && !name.trim().equals("")) {
            if (password != null && !password.trim().equals("")) {
                User u = userDao.selectByNameAndPassword(name, password);
                if (u != null) {
                    return u;
                } else {
                    throw new RuntimeException("用户名或密码错误");
                }
            } else {
                throw new RuntimeException("密码为空");
            }
        } else {
            throw new RuntimeException("用户名为空");
        }
    }

    @Override
    public User getUserInfoByName(String name) {
        if (name != null && !name.trim().equals("")) {
            User user = userDao.selectInfoByName(name);
            if (user != null) {
                return user;
            } else {
                throw new RuntimeException("用户不存在");
            }
        }else {
            throw new RuntimeException("用户名为空");
        }
    }

    @Override
    public boolean register(User user) {
        if (user.getName() != null && !user.getName().trim().equals("")) {
            String u = userDao.selectNameByName(user.getName());
            if (u != null) {
                throw new RuntimeException("用户名已存在");
            }
            if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
                int ret = userDao.insert(user);
                if (ret > 0) {
                    return true;
                } else {
                    throw new RuntimeException("注册失败");
                }
            } else {
                throw new RuntimeException("密码为空");
            }
        } else {
            throw new RuntimeException("用户名为空");
        }
    }

    @Override
    public List<User> getTopStars(Integer count) {
        if (count != null && count > 0) {
            List<User> users = userDao.selectTopStars(count);
            return users;
        } else {
            throw new RuntimeException("数量无效");
        }
    }

    @Override
    public List<User> getTopScores(Integer count) {
        if (count != null && count > 0) {
            List<User> users = userDao.selectTopScores(count);
            return users;
        } else {
            throw new RuntimeException("数量无效");
        }
    }
}