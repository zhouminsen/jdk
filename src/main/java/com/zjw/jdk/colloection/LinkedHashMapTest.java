package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoum on 2018/5/3.
 */
public class LinkedHashMapTest {

    @Test
    public void test() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("zjw1", "1");
        linkedHashMap.put("zjw2", "2");
        linkedHashMap.put("zjw3", "3");
        Iterator<Map.Entry<String, String>> iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next);
        }
    }
}
