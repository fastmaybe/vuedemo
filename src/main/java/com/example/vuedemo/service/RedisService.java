package com.example.vuedemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.vuedemo.pojo.User;
import com.example.vuedemo.util.redis.RedisUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<String, String> template;

    //    @PostConstruct
    public void test(String key, Integer count) {
        if (null == count || Strings.isEmpty(count + "")) {
            count = 100;
        }
        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testThread(key);
                }
            }).start();
        }
    }

    public void testThread(String key) {
        if (redisUtil.Lock(key)) {
            System.out.println("加锁");
        } else {
            System.out.println("NO");
        }
    }

    public void test2(Integer count) {
        if (null == count || Strings.isEmpty(count + "")) {
            count = 100;
        }
        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    query2();

                }
            }).start();
            int a=100;
            a+=100;
            try {
                Thread.sleep((long) (a*1.0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public List<User> query() {
        String o = (String) template.boundHashOps("user").get("user");
        List<User> users = JSONObject.parseArray(o, User.class);
        if (null == users) {
            System.out.println("数据库");
            List<User> userList = new ArrayList<>();
            User user = new User();
            user.setUsername("iii");
            userList.add(user);
            JSONArray objects = JSONArray.parseArray(JSON.toJSONString(userList));
            template.boundHashOps("user").put("users", objects.toString());
            return userList;
        } else {
            System.out.println("缓存");
        }
        return users;
    }


    public  void query2() {
        String a = template.opsForValue().get("19");
        if (null == a || Strings.isEmpty(a)) {
            template.opsForValue().set("19", "19");
            System.out.println("数据库");
        } else {
            System.out.println("缓存");
        }
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUsername("aaa");
        userList.add(user);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(userList));
        System.out.println(array.toJSONString());
        System.out.println(array.getClass());
        List<User> list2 = JSONObject.parseArray(array.toJSONString(), User.class);
        System.out.println(list2);
    }
}
