package com.zjw.jdk.thread;

import lombok.Data;

/**
 * Created by zhoum on 2018/4/27.
 */
@Data
public class VolatileTest extends Thread {
    volatile boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("进入run方法");
        while (isRunning) {
        }
        System.out.println("我退出run方法");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest v = new VolatileTest();
        new Thread(v).start();

        Thread.sleep(1000);
        v.setRunning(false);
        System.out.println("我主线程已经设置为false了");
    }
}



