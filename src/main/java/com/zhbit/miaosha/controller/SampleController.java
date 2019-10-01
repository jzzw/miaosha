package com.zhbit.miaosha.controller;

import com.zhbit.miaosha.Result.Result;
import com.zhbit.miaosha.domain.User;
import com.zhbit.miaosha.redis.RedisService;
import com.zhbit.miaosha.redis.UserKey;
import com.zhbit.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Joshu");
        return "hello";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result<User> getUser(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }

}
