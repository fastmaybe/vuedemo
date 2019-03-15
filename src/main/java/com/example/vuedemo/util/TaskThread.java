package com.example.vuedemo.util;


import com.example.vuedemo.service.ThreadService;


public class TaskThread implements  Runnable {

    private ThreadService service;
    private String name;
    public TaskThread(String name,ThreadService service){
        this.service=service;
        this.name=name;
    }
    @Override
    public void run() {
        service.queryAll();
    }
}
