package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class ThreadDaemon extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        ThreadDaemon threadDaemon = new ThreadDaemon();
        threadDaemon.setDaemon(true);
        threadDaemon.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
