package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class DeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() throws Exception {
        synchronized (left) {
            Thread.sleep(1000);
            synchronized (right) {
                System.out.println("拿到right锁");
            }
        }
    }

    public void rightLeft() throws Exception {
        synchronized (right) {
            Thread.sleep(1000);
            synchronized (left) {
                System.out.println("拿到left锁");
            }
        }
    }

    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        Thread t0 = new Thread(() -> {
            try {
                dl.leftRight();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        Thread t1 = new Thread(() -> {
            try {
                dl.rightLeft();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t0.start();
        t1.start();
    }

}
