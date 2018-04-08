package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class ThreadJoin extends Thread {

    public ThreadJoin(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadJoin("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                ThreadJoin tj = new ThreadJoin("被join的线程");
                tj.start();
                tj.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
