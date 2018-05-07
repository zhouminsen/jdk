package com.zjw.jdk.colloection;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zhoum on 2018/5/3.
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(list);
        for (Integer item : list) {
            list.remove(item);
        }
        System.out.println(list);
    }
}
