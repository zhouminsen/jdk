package com.zjw.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoum on 2018/5/16.
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(bb());
            });
            executorService.submit(thread);
        }
        executorService.shutdown();
        System.out.println("111我是主线程");


    }

    private static int bb() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        System.out.println("我是主线程");
        return 1;
    }
}
