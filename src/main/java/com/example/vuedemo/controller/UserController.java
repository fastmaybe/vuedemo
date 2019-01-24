package com.example.vuedemo.controller;


import com.example.vuedemo.pojo.Result;
import com.example.vuedemo.pojo.User;
import com.example.vuedemo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("login")
    public Result login(User user) {
        return  userService.login(user);
    }

    @RequestMapping("add")
    public Result add( User user) {
      return userService.add(user);
    }

    @RequestMapping("delete")
    public Result delete(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }
    @RequestMapping("update")
    public Result update(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }
    @RequestMapping("edit")
    public Result edit(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }


}
