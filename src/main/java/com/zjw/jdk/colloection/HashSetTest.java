package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhoum on 2018/5/3.
 */
public class HashSetTest {

    @Test
    public void add() {
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("1");
        set.add("1");
        System.out.println(set);
    }
}
