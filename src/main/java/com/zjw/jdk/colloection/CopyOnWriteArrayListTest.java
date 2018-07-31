package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhoum on 2018/5/3.
 */
public class CopyOnWriteArrayListTest {
    @Test
    public void test() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(list);
        for (Integer item : list) {
            list.remove(item);
        }
        System.out.println(list);
    }
}
