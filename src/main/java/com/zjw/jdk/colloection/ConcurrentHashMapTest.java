package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhoum on 2018/5/7.
 */
public class ConcurrentHashMapTest {

    @Test
    public void test() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("zjw", "zjw");
        System.out.println(concurrentHashMap);
    }
}
