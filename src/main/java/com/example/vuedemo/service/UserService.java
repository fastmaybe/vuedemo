package com.example.vuedemo.service;

import com.example.vuedemo.mapper.UserMapper;
import com.example.vuedemo.pojo.Result;
import com.example.vuedemo.pojo.User;
import com.example.vuedemo.util.SnowFlake;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Result login(User user){
        return null;
    }

    public Result add(User user) {
        long id = SnowFlake.nextId();
        user.setId(id);
        System.out.println(id);
        userMapper.insert(user);
        System.out.println();
        System.out.println("assa");
        System.out.println("阿萨飒飒");
        return null;
    }
}
