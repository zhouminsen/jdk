package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class DeadLock2 {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() throws Exception {
        synchronized (left) {
            Thread.sleep(2000);
            synchronized (right) {
                System.out.println("leftRight end!");
            }
        }
    }

    public void rightLeft() throws Exception {
        synchronized (right) {
            Thread.sleep(2000);
            synchronized (left) {
                System.out.println("rightLeft end!");
            }
        }
    }

    public static void main(String[] args) {
        //new 内部类
        DeadLock2 dl = new DeadLock2();
        Thread0 t0 = dl.new Thread0(dl);
        Thread1 t1 = dl.new Thread1(dl);
        t0.start();
        t1.start();

//        while(true);
    }

    class Thread0 extends Thread {
        private DeadLock2 dl;

        public Thread0(DeadLock2 dl) {
            this.dl = dl;
        }

        public void run() {
            try {
                dl.leftRight();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Thread1 extends Thread {
        private DeadLock2 dl;

        public Thread1(DeadLock2 dl) {
            this.dl = dl;
        }

        public void run() {
            try {
                dl.rightLeft();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
