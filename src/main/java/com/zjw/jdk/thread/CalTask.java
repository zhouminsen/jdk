package com.zjw.jdk.thread;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by zhoum on 2018/4/4.
 */
@Data
public class CalTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20;

    //每个小人物最多只累加20个数
    private int[] arr;

    private int start;
    private int end;

    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值:" + i);
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            System.out.println(Thread.currentThread().getName() + "的middle值:" + middle);
            //将大任务分成2个任务
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            return left.join() + right.join();

        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] arr = new int[100];
        Random random = new Random();
        int total = 0;
        for (int i = 0; i <arr.length; i++) {
            int tmp = random.nextInt(20);
            total += arr[i] = tmp;
        }
        System.out.println(total);
        ForkJoinPool pool = null;//ForkJoinPool.commonPool();
        //提交可分解的任务
        Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        System.out.println(future.get());
        pool.shutdown();
    }
}
