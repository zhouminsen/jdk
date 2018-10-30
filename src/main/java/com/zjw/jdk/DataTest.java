package com.zjw.jdk;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhoum on 2018/1/8.
 */
public class DataTest {

    @Test
    public void test() throws InterruptedException {
        Long date = new Date().getTime();
        System.out.println(date);
        Thread.sleep(1 * 60 * 1000);
        System.out.println((System.currentTimeMillis() - date) < 1 * 60 * 1000);

    }

    @Test
    public void test2() throws InterruptedException {
        for (int i = 0; i <3 ; i++) {
            System.out.println(System.currentTimeMillis());
        }

    }

    @Test
    public void test3() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(format.format(new Date(1538356851380L)));
    }
}
