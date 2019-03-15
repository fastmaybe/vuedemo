package com.example.vuedemo.controller;

import com.example.vuedemo.service.RedisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    @PostMapping("test")
    public void teseRedis(String key,Integer count){
        redisService.redisTest(key,count);
    }

}
