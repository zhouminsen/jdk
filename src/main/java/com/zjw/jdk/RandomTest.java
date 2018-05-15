package com.zjw.jdk;

import java.util.Random;

/**
 * Created by zhoum on 2018/5/8.
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(1000000));
        }
    }
}
