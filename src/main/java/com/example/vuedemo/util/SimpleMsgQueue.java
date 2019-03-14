package com.example.vuedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleMsgQueue {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);

    public boolean tryAdd(String msg) {
        boolean result = false;
        try {
            result = queue.add(msg);
        } catch (IllegalStateException e) {
            logger.warn("队列已满，消息插入失败：" + msg);
        }
        return result;
    }

    public String take() throws InterruptedException {
        return queue.poll();
    }

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date(1552536543051L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        System.out.println(format1);

    }
}
