package com.zjw.jdk.thread;

/**
 * Created by zhoum on 2018/4/3.
 */
public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    public void init() {
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("主线程");
        b.foo(a);
        System.out.println("进入主线程之后");
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock).start();
        deadLock.run();
    }
}

class A {
    public synchronized void foo(B b) {
        System.out.println("当前线程名:" + Thread.currentThread().getName() + "进入a实列foo()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名:" + Thread.currentThread().getName() + "进入b实列last()");
        b.last();
    }

    public synchronized void last() {
        System.out.println("进入a类last()");
    }
}

class B {
    public synchronized void foo(A a) {
        System.out.println("当前线程名:" + Thread.currentThread().getName() + "进入b实列foo()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程名:" + Thread.currentThread().getName() + "进入a实列last()");
        a.last();
    }

    public synchronized void last() {
        System.out.println("进入b类last()");
    }
}
