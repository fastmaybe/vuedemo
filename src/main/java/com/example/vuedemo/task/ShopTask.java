package com.example.vuedemo.task;

import org.apache.logging.log4j.util.Strings;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ShopTask implements Runnable {
    private int customerId;

    private JedisPool jedisPool;

    public ShopTask (int customerId, JedisPool jedisPool) {
        this.customerId = customerId;
        this.jedisPool = jedisPool;
    }
    @Override
    public void run() {
        Jedis resource = jedisPool.getResource();
        String productlist = resource.lpop("productlist");
        if (null!=productlist && Strings.isEmpty(productlist)){
            System.out.println("恭喜");
        }else {
            System.out.println("下次再来");
        }
    }
}
