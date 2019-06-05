package com.example.login.entity;

import com.example.login.R;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class LevelMapImpl implements LevelMap {
    private static final int MapHeight = 5;
    private static final int MapWidth = 4;

    private static final Set<Integer> BlankIndex = new HashSet<>();

    static {
        BlankIndex.add(10);
        BlankIndex.add(11);
    }

    public static final String DefaultMapString = "0116011734453825a92b";
    //    public static final String DefaultMapString = "01120112344537856ab9";
//    public static final String DefaultMapXYString = "001030021232041323341424";
    public static final String DefaultMapXYString = "001023021232303113140434";

    private static int charToInt(char ch) {
        return Integer.parseInt(String.valueOf(ch), 16);
    }

    private String name;
    private String mapString;
    private String mapXYString;
    private int[][] mapValue = new int[MapHeight][MapWidth];

    public LevelMapImpl(String name, String mapString, String mapXYString) {
        this.name = name;
        this.mapString = mapString;
        this.mapXYString = mapXYString;
        int index = 0;
        for (int i = 0; i < MapHeight; i++) {
            for (int j = 0; j < MapWidth; j++) {
                char ch = mapString.charAt(index++);
                mapValue[i][j] = charToInt(ch);
            }
        }
        index = 0;
        for (int i = 0, len = Role.getRoles().size(); i < len; i++) {
            Role.getRole(i).setX(charToInt(mapXYString.charAt(index++)));
            Role.getRole(i).setY(charToInt(mapXYString.charAt(index++)));
        }
    }

    private Integer getIndexByXY(int x, int y) {
        if (x < 0 || x >= MapWidth || y < 0 || y >= MapHeight) {
            return null;
        }
        return mapValue[y][x];
    }

    private void setIndexByXY(int x, int y, int value) {
        if (x < 0 || x >= MapWidth || y < 0 || y >= MapHeight) {
            return;
        }
        mapValue[y][x] = value;
    }

    @Override
    public Integer getLeft(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX() - 1;
        int y = r.getY();
        Integer i = getIndexByXY(x, y);
        if (i == null) {
            return null;
        } else {
            return Role.getRole(i).getId();
        }
    }

    @Override
    public Integer getRight(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX() + r.getWidth();
        int y = r.getY();
        Integer i = getIndexByXY(x, y);
        if (i == null) {
            return null;
        } else {
            return Role.getRole(i).getId();
        }
    }

    @Override
    public Integer getUp(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX();
        int y = r.getY() - 1;
        Integer i = getIndexByXY(x, y);
        if (i == null) {
            return null;
        } else {
            return Role.getRole(i).getId();
        }
    }

    @Override
    public Integer getDown(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX();
        int y = r.getY() + r.getHeight();
        Integer i = getIndexByXY(x, y);
        if (i == null) {
            return null;
        } else {
            return Role.getRole(i).getId();
        }
    }

    @Override
    public boolean canLeft(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX() - 1;
        for (int y = r.getY(), dy = 0; dy < r.getHeight(); dy++) {
            Integer i = getIndexByXY(x, y + dy);
            if (!BlankIndex.contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canRight(int id) {
        Role r = Role.getRoleById(id);
        int x = r.getX() + r.getWidth();
        for (int y = r.getY(), dy = 0; dy < r.getHeight(); dy++) {
            Integer i = getIndexByXY(x, y + dy);
            if (!BlankIndex.contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canUp(int id) {
        Role r = Role.getRoleById(id);
        int y = r.getY() - 1;
        for (int x = r.getX(), dx = 0; dx < r.getWidth(); dx++) {
            Integer i = getIndexByXY(x + dx, y);
            if (!BlankIndex.contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canDown(int id) {
        Role r = Role.getRoleById(id);
        int y = r.getY() + r.getHeight();
        for (int x = r.getX(), dx = 0; dx < r.getWidth(); dx++) {
            Integer i = getIndexByXY(x + dx, y);
            if (!BlankIndex.contains(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void goLeft(int id) {
        if (canLeft(id)) {
            Role r = Role.getRoleById(id);
            int x = r.getX();
            int y = r.getY();
            int newX = x - 1;
            int kongX = x - 1;
            int kongNewX = x + r.getWidth() - 1;
            int index = getIndexByXY(x, y);
            for (int dy = 0; dy < r.getHeight(); dy++) {
                int kongIndex = getIndexByXY(kongX, y + dy);
                setIndexByXY(kongNewX, y + dy, kongIndex);
                Role.getRole(kongIndex).setX(kongNewX);
            }
            r.setX(newX);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                for (int dy = 0; dy < r.getHeight(); dy++) {
                    setIndexByXY(newX + dx, y + dy, index);
                }
            }
        }
    }

    @Override
    public void goRight(int id) {
        if (canRight(id)) {
            Role r = Role.getRoleById(id);
            int x = r.getX();
            int y = r.getY();
            int newX = x + 1;
            int kongX = x + r.getWidth();
            int kongNewX = x;
            int index = getIndexByXY(x, y);
            for (int dy = 0; dy < r.getHeight(); dy++) {
                int kongIndex = getIndexByXY(kongX, y + dy);
                setIndexByXY(kongNewX, y + dy, kongIndex);
                Role.getRole(kongIndex).setX(kongNewX);
            }
            r.setX(newX);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                for (int dy = 0; dy < r.getHeight(); dy++) {
                    setIndexByXY(newX + dx, y + dy, index);
                }
            }
        }
    }

    @Override
    public void goUp(int id) {
        if (canUp(id)) {
            Role r = Role.getRoleById(id);
            int x = r.getX();
            int y = r.getY();
            int newY = y - 1;
            int kongY = y - 1;
            int kongNewY = y + r.getHeight() - 1;
            int index = getIndexByXY(x, y);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                int kongIndex = getIndexByXY(x + dx, kongY);
                setIndexByXY(x + dx, kongNewY, kongIndex);
                Role.getRole(kongIndex).setY(kongNewY);
            }
            r.setY(newY);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                for (int dy = 0; dy < r.getHeight(); dy++) {
                    setIndexByXY(x + dx, newY + dy, index);
                }
            }
        }
    }

    @Override
    public void goDown(int id) {
        if (canDown(id)) {
            Role r = Role.getRoleById(id);
            int x = r.getX();
            int y = r.getY();
            int newY = y + 1;// 当前棋子的新位置
            int kongY = y + r.getHeight();// 当前空所在的位置
            int kongNewY = y;// 空的新位置
            int index = getIndexByXY(x, y);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                int kongIndex = getIndexByXY(x + dx, kongY);
                setIndexByXY(x + dx, kongNewY, kongIndex);
                Role.getRole(kongIndex).setY(kongNewY);
            }
            r.setY(newY);
            for (int dx = 0; dx < r.getWidth(); dx++) {
                for (int dy = 0; dy < r.getHeight(); dy++) {
                    setIndexByXY(x + dx, newY + dy, index);
                }
            }
        }
    }

    @Override
    public boolean success() {
        Role caocao = Role.getRoleById(R.id.caocao);
        return caocao.getX() == 1 && caocao.getY() == 3;
    }

    // TODO
    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

}
