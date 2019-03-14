package com.example.vuedemo.component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class TaskCache  {

    public static Map<Integer, ScheduledFuture<?>> futures = new HashMap<>();
}
