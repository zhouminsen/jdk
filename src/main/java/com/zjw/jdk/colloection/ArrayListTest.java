package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by zhoum on 2018/7/9.
 */
public class ArrayListTest {
    @Test
    public void test() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }

    }
}
