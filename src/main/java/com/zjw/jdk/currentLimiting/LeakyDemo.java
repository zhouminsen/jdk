package com.zjw.jdk.currentLimiting;

public class LeakyDemo {
    public static long time = System.currentTimeMillis();
    // 桶里现在的水
    public static int water = 0;
    // 桶的大小
    public static int size = 10;
    // 水漏出的速度
    public static int rate = 3;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        //计算出水的数量
        int out = (int) ((now - time) / 700 * rate);
        //漏水后剩余
        water = Math.max(0, water - out);
        time = now;
        if ((water + 1) < size) {
            ++water;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
                    if (grant()) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流啦!");
                    }
                }
//            }).start();
//        }
    }
}