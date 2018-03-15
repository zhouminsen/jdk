package com.zjw.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zhoum on 2018/1/26.
 */
public class CallTest   {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "的循环变量i的值" + i);
                    Thread.sleep(10000);
                }
                return i;
            }
        });
        new Thread(task).start();
        System.out.println("我是主线程");
        System.out.println("任务执行完成:"+task.get());
    }
}
