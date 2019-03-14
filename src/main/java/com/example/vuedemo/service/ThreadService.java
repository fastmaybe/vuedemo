package com.example.vuedemo.service;

import com.example.vuedemo.component.TaskCache;
import com.example.vuedemo.util.TaskThread;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.*;

@Service
public class ThreadService {
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    public void queryAll() {
              for (int i = 1; i <=1; i++) {
                  System.out.println(Thread.currentThread().getName()+"  :开始:  "+i);
                  try {
                      Thread.sleep(2);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println("耗时5秒");
              }
    }

    public void start() {
        for (int i = 1; i <= 2; i++) {
//            TaskCache.futures.put(i,threadPoolTaskScheduler.schedule(new TaskThread("线程"+i,this),new Date(new Date().getTime()+1000L)));
            TaskCache.futures.put(i,threadPoolTaskScheduler.schedule(new TaskThread("线程"+i,this),new CronTrigger("*/5 * * * * ?")));
        }
    }

    public void stop(Integer id) {
        ScheduledFuture<?> scheduledFuture = TaskCache.futures.get(id);
//        System.out.println( scheduledFuture);
//        boolean cancel =scheduledFuture.cancel(true);
        try {
            scheduledFuture.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(cancel);

    }
}
