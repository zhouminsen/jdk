package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.Hashtable;

/**
 * Created by zhoum on 2018/5/14.
 */
public class HashtableTest {
    @Test
    public void test() {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        hashtable.put(1, 2);
        hashtable.put(3, 2);
        hashtable.put(2, 2);
        System.out.println(hashtable);
    }
}
