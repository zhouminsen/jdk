package com.zjw.jdk.currentLimiting;

/**
 * Created by Administrator on 2018/9/5.
 */
public class CounterTest {
    private static long timeStamp = System.currentTimeMillis();
    //限制请求次数
    private static long limitCount = 100;

    private static long interval = 1000;
    //请求数
    private static long reqCount = 0;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            if (reqCount < limitCount) {
                ++reqCount;
                return true;
            } else {
                return false;
            }
        } else {
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (grant()) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流啦!");
                    }
                }
            }).start();
        }
    }

}
