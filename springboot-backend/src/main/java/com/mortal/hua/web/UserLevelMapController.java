package com.mortal.hua.web;

import com.mortal.hua.entity.UserLevelMap;
import com.mortal.hua.service.UserLevelMapService;
import com.mortal.hua.util.http.FormatResponseUtil;
import com.mortal.hua.util.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userLevelMap")
public class UserLevelMapController {
    @Autowired
    UserLevelMapService userLevelMapService;

    @PostMapping("/addOrUpdateUserRecord")
    public ResponseResult addOrUpdateUserRecord(@RequestParam("userName") String userName, @RequestParam("levelMapName") String levelMapName, @RequestParam("timeMs") Long timeMs, @RequestParam("stepCount") Long stepCount) {
        UserLevelMap u = new UserLevelMap();
        u.setUserName(userName);
        u.setLevelMapName(levelMapName);
        u.setTimeMs(timeMs);
        u.setStepCount(stepCount);
        userLevelMapService.fill(u);
        System.out.println("----===");
        System.out.println(u);
        // TODO判断 levelmap topid

        UserLevelMap old = userLevelMapService.getUserRecordByUserNameAndLevelMapName(userName, levelMapName);
        if (u.isHigher(old)) {
            return FormatResponseUtil.formatResponse(userLevelMapService.addOrUpdateUserRecord(u));
        } else {
            return FormatResponseUtil.formatResponse(u);
        }
    }

    @PostMapping("/getUserRecordsByUserName")
    public ResponseResult getUserRecordsByUserName(@RequestParam("userName") String userName) {
        for (int i = 0; i < 10; i++) {
            System.out.println(userName);
        }
        return FormatResponseUtil.formatResponse(userLevelMapService.getUserRecordsByUserName(userName));
    }

    @PostMapping("/getUserRecordByUserNameAndLevelMapName")
    public ResponseResult getUserRecordByUserNameAndLevelMapName(@RequestParam("userName") String userName, @RequestParam("levelMapName") String levelMapName) {
        return FormatResponseUtil.formatResponse(userLevelMapService.getUserRecordByUserNameAndLevelMapName(userName, levelMapName));
    }
}
