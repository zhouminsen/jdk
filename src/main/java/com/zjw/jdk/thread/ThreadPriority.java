package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class ThreadPriority extends Thread {

    public ThreadPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + ",其优先级是:" + getPriority() + ",循环变量值为:" + i);
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        for (int i = 0; i < 30; i++) {
            if (i == 10) {
                ThreadPriority low = new ThreadPriority("低级");
                low.start();
                System.out.println("创建之初的优先级:" + low.getPriority());
                low.setPriority(Thread.MIN_PRIORITY);
            }
            if (i == 20) {
                ThreadPriority high = new ThreadPriority("高级");
                high.start();
                System.out.println("创建之初的优先级:" + high.getPriority());
                high.setPriority(Thread.MAX_PRIORITY);
            }
        }
    }
}
