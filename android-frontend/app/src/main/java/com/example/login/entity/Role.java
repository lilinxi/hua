package com.example.login.entity;

import com.example.login.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Role {
    private int id;
    private int width;
    private int height;
    private int x;
    private int y;

    private Role(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    private static List<Role> Roles = new ArrayList<>();
    private static Map<Integer, Role> RolesMap = new HashMap<>();

    private static void registerRole(Role role) {
        Roles.add(role);
        RolesMap.put(role.getId(), role);
    }

    static {
        Role role = new Role(R.id.machao, 1, 2);
        registerRole(role);
        role = new Role(R.id.caocao, 2, 2);
        registerRole(role);
        role = new Role(R.id.zhaoyun, 1, 2);
        registerRole(role);
        role = new Role(R.id.huangzhong, 1, 2);
        registerRole(role);
        role = new Role(R.id.guanyu, 2, 1);
        registerRole(role);
        role = new Role(R.id.zhangfei, 1, 2);
        registerRole(role);
        role = new Role(R.id.bing1, 1, 1);
        registerRole(role);
        role = new Role(R.id.bing2, 1, 1);
        registerRole(role);
        role = new Role(R.id.bing3, 1, 1);
        registerRole(role);
        role = new Role(R.id.bing4, 1, 1);
        registerRole(role);
        role = new Role(R.id.kong1, 1, 1);
        registerRole(role);
        role = new Role(R.id.kong2, 1, 1);
        registerRole(role);
    }

    static Role getRole(int index) {
        return Roles.get(index);
    }

    static Role getRoleById(int id) {
        return RolesMap.get(id);
    }

    public static List<Role> getRoles() {
        return Roles;
    }
}
