package com.example.vuedemo.scheduled;

public class TaskFor implements Runnable {
    private int count=3;

    @Override
    public void run() {
        try {
            count--;
            int a=10/count;
            System.out.println("run "+ System.currentTimeMillis()+"---  "+a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
