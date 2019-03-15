package com.example.vuedemo.controller;


import com.example.vuedemo.pojo.Result;
import com.example.vuedemo.pojo.User;
import com.example.vuedemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api
@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value="获取用户列表")
    @PostMapping("login")
    public Result login(User user) {
        return  userService.login(user);
    }

    @PostMapping("add")
    public Result add( User user) {
      return userService.add(user);
    }

    @PostMapping("delete")
    public Result delete(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }
    @PostMapping("update")
    public Result update(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }
    @PostMapping("edit")
    public Result edit(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }


}
