package com.zjw.jdk.colloection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhoum on 2018/5/7.
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("zjw", "zjw");
        System.out.println(concurrentHashMap);
    }
}
