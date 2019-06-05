package com.mortal.hua.web;

import com.mortal.hua.entity.User;
import com.mortal.hua.service.UserService;
import com.mortal.hua.util.http.FormatResponseUtil;
import com.mortal.hua.util.http.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController注解相当于@ResponseBody ＋ @Controller
//1. 使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
//2.若返回json等内容到页面，则需要加@ResponseBody注解@RestController
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。
//    @PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。

    @GetMapping("/test")
    public ResponseResult test() {
        return FormatResponseUtil.formatResponse();
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestParam("name") String name, @RequestParam("password") String password) {
        return FormatResponseUtil.formatResponse(userService.login(name, password));
    }

    @PostMapping("/getUserInfo")
    public ResponseResult getUserInfo(@RequestParam("name") String name) {
        return FormatResponseUtil.formatResponse(userService.getUserInfoByName(name));
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestParam("name") String name, @RequestParam("password") String password) {
        System.out.println(name);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return FormatResponseUtil.formatResponse(userService.register(user));
    }

    @PostMapping("/getTopStars")
    public ResponseResult getTopStars(@RequestParam("count") Integer count) {
        System.out.println(count);
        return FormatResponseUtil.formatResponse(userService.getTopStars(count));
    }

    @PostMapping("/getTopScores")
    public ResponseResult getTopScores(@RequestParam("count") Integer count) {
        return FormatResponseUtil.formatResponse(userService.getTopScores(count));
    }
}
