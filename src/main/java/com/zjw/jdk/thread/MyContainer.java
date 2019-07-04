package com.zjw.jdk.thread;

import java.util.LinkedList;

/**
 * Created by zhoum on 2019-07-04.
 */
public class MyContainer<T> {
    LinkedList<T> linkedList = new LinkedList<>();
    private Integer max = 10;

    public synchronized void put(T t) {
        //如果当前容器==10，停止生产, 通知到消费者消费
        while (linkedList.size() == max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        System.out.println(Thread.currentThread().getName() + " p");
        //通知到消费者消费
        notifyAll();
    }


    public synchronized T get() {
        //当前容器==0，停止消费，通知到生产者生产
        while (linkedList.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = linkedList.removeFirst();
        System.out.println(Thread.currentThread().getName() + " c");
        //通知生产者生产
        notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer<String> container = new MyContainer<>();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        container.put(Thread.currentThread().getName() + " " + j);
                    }
                }
            }, "p" + i).start();
        }

        try {
            //休眠，让生产者准备好数据
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        String s = container.get();
//                        System.out.println("c  " + s);
                    }
                }
            }, "c" + i).start();
        }
    }
}
