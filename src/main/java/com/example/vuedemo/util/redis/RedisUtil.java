package com.example.vuedemo.util.redis;

import com.example.vuedemo.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,String> template;

    public Boolean Lock(String key){
        Boolean aBoolean = template.opsForValue().setIfAbsent(key, key, 10, TimeUnit.SECONDS);
        return aBoolean;
    }

    public void store(List<User> users){
        template.boundHashOps("user").put("users",users);
    }


}
