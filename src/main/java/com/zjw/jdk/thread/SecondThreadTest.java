package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/1/26.
 */
public class SecondThreadTest implements Runnable {

    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        SecondThreadTest st = new SecondThreadTest();
        new Thread(st, "线程1").start();
        new Thread(st, "线程2").start();
    }
}
