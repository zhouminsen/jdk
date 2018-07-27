package com.zjw.jdk.colloection;

import java.util.*;

/**
 * Created by zhoum on 2018/5/3.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap(19);
        map.put("Aa", "c");
        map.put("BB", "c");
        map.put("俗话说", "c");
        map.put("27662", "c");
//        map.remove("Aa");
        Map<String,String> map2=new HashMap<>(2);
        map2.put("柳志崇","柳志崇");
        map2.put("柳山왡","柳山왡");
        map2.putAll(map);
        System.out.println("柳志崇".hashCode() + "," + "柳山왡".hashCode());

        Set<Map.Entry<String, String>> entries = map2.entrySet();
        /*for (Map.Entry<String, String> item : entries) {
            System.out.println(item.getKey()+","+item.getValue());
        }*/
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+","+next.getValue());
        }

        System.out.println(Integer.parseInt("0001111", 2) & 15);
        System.out.println(Integer.parseInt("0011111", 2) & 15);
        System.out.println(Integer.parseInt("0111111", 2) & 15);
        System.out.println(Integer.parseInt("1111111", 2) & 15);
        System.out.println();
        System.out.println("0111111".hashCode());
        System.out.println("0011111".hashCode());
        System.out.println("0001111".hashCode());
        System.out.println("1111111".hashCode());

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 2);
        hashMap.put(3, 2);
        hashMap.put(2, 2);
        System.out.println(hashMap);
    }
}
