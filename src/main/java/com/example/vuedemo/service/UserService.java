package com.example.vuedemo.service;

import com.example.vuedemo.mapper.UserMapper;
import com.example.vuedemo.pojo.Result;
import com.example.vuedemo.pojo.User;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Result login(User user){
        return null;
    }




}
