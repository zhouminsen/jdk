package com.zjw.jdk.thread;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhoum on 2018/4/4.
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(1);
        bq.put("java");
        bq.put("java");
        bq.add("java");
        System.out.println(bq.offer("java"));

     /*   new producer(bq).start();
        new producer(bq).start();
        new producer(bq).start();

        new Consumer(bq).start();*/


    }

    @Data
    static class producer extends Thread {
        private BlockingQueue<String> bq;


        public producer(BlockingQueue<String> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            String[] strArr = {"java", "struts", "spring"};
            for (int i = 0; i < 9999999; i++) {
                System.out.println(getName() + "生产者准备生产集合元素!");
                try {
                    Thread.sleep(200);
                    bq.put(strArr[i % 3]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + "生产完成:" + bq);
            }
        }
    }

    @Data
    static class Consumer extends Thread {
        private BlockingQueue<String> bq;


        public Consumer(BlockingQueue<String> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(getName() + "消费者准备消费集合元素");
                try {
                    Thread.sleep(200);
                    bq.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + "消费完成:" + bq);
            }
        }
    }
}




