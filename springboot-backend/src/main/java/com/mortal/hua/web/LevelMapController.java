package com.mortal.hua.web;

import com.mortal.hua.service.LevelMapService;
import com.mortal.hua.util.http.FormatResponseUtil;
import com.mortal.hua.util.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/levelMap")
public class LevelMapController {

    @Autowired
    LevelMapService levelMapService;

    @PostMapping("/getLevelMap")
    public ResponseResult getLevelMap(String name) {
        ResponseResult r = FormatResponseUtil.formatResponse(levelMapService.getLevelMapByName(name));
        System.out.println(r);
        System.out.println("==");
        return r;
    }

    @GetMapping("/updateTopId")
    public ResponseResult updateTopId(String n) {
        return FormatResponseUtil.formatResponse(levelMapService.updateTopIdByName(n,7));
    }
}
