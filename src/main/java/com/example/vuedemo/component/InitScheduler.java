package com.example.vuedemo.component;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
public class InitScheduler {

    @Bean
    ThreadPoolTaskScheduler getThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        threadPoolTaskScheduler.setDaemon(true);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        return  threadPoolTaskScheduler;
    }
}
