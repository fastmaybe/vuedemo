package com.example.vuedemo.service;


import com.example.vuedemo.task.ShopTask;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShopService {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxTotal(1024);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.88.160", 6379);
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
//            executor.execute(new ShopTask());
        }
    }
}
