package com.zjw.jdk.colloection;

import java.util.Hashtable;

/**
 * Created by zhoum on 2018/5/14.
 */
public class HashtableTest {
    public static void main(String[] args) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        hashtable.put(1, 2);
        hashtable.put(3, 2);
        hashtable.put(2, 2);
        System.out.println(hashtable);
    }
}
