package com.zjw.jdk.colloection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoum on 2018/5/3.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
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


    }
}
