package com.example.vuedemo.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTask {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture1 = executorService.scheduleAtFixedRate(new TaskFor(), 0, 3000, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> scheduledFuture2 = executorService.scheduleAtFixedRate(new TaskFor(), 0, 3000, TimeUnit.MILLISECONDS);

    }
}
