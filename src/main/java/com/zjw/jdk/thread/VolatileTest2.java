package com.zjw.jdk.thread;

public class VolatileTest2 {
    public static volatile int a = 0;
    public static final int THREAD_COUNT = 20;

    public static void increase() {
        a++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(a);
    }
}
