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

    public static class RunThread {

        private boolean isRunning = true;

        private void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        public void run1() {
            while (isRunning) {
            }
        }

        public static void main(String[] args) throws InterruptedException {
            final RunThread rt = new RunThread();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    rt.run1();
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    rt.setRunning(false);
                    while (true) {
                    }
                }
            });
            t.start();
            t2.start();
        }
    }
}



