package com.example.vuedemo.controller;


import com.example.vuedemo.pojo.Result;
import com.example.vuedemo.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping("login")
    public Result login(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }

    @RequestMapping("add")
    public Result add(@RequestBody User user) {
        System.out.println(user);
        return new Result(true, "ok");
    }

}
