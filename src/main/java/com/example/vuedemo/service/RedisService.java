package com.example.vuedemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.vuedemo.pojo.User;
import com.example.vuedemo.util.redis.RedisClient;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {

    private int a=1;
    @Resource
  private RedisClient redisClient;

    public void redisTest(String key,Integer count){
        for (int i = 0; i <count ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    aaa(key);
                }
            }).start();

        }
    }

     void aaa(String key){
        a++;
        if (a==2){
            System.out.println("aaaaaaaaa");
        }
    }
}
