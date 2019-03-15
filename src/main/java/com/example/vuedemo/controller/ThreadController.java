package com.example.vuedemo.controller;

import com.example.vuedemo.service.ThreadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("thread")
public class ThreadController {
    @Resource
    private ThreadService service;

    @RequestMapping("start")
    public void start() {
        service.start();
    }

    @RequestMapping("stop")
    public void stop(Integer id) {
        service.stop(id);
    }
}
