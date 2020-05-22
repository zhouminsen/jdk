package com.zjw.jdk.colloection;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhoum on 2018/5/3.
 */
public class HashMapTest {

    /**
     * put和putAll的过程
     */
    @Test
    public void test() {
        HashMap<String, String> map = new HashMap(20);
        map.put("Aa", "c");
        map.put("BB", "c");
        map.put("俗话说", "c");
        map.put("27662", "c");
        //初始化2,添加大于2是扩容
        Map<String, String> map2 = new HashMap<>(2);
        map2.put("柳志崇", "柳志崇");
        map2.put("柳山왡", "柳山왡");
        map2.putAll(map);
        System.out.println("柳志崇".hashCode() + "," + "柳山왡".hashCode());
    }

    /**
     * 迭代
     */
    @Test
    public void test2() {
        Map<String, String> map = new HashMap();
        map.put("Aa", "c");
        map.put("BB", "c");
        map.put("俗话说", "c");
        map.put("27662", "c");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> item : entries) {
            System.out.println(item.getKey() + "," + item.getValue());
        }
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + "," + next.getValue());
        }
    }

    /**
     * hashcode一样的字符串
     */
    @Test
    public void test3() {
        Map<String, String> map2 = new HashMap<>(2);
        map2.put("柳志崇", "柳志崇");
        map2.put("柳山왡", "柳山왡");
        System.out.println("柳志崇".hashCode() + "," + "柳山왡".hashCode());
    }

    /**
     *
     */
    @Test
    public void test4() {
        Map<String, String> map2 = new HashMap<>();
        map2.put("1", "1");
        map2.put("1", "2");
        System.out.println(map2);
    }

    /**
     *
     */
    @Test
    public void test5() {
        Map<String, String> map2 = new HashMap<>();
        map2.put("5", "5");
        map2.put("1", "1");
        map2.put("2", "2");
        map2.put("4", "10");
        map2.put("3", "3");
        System.out.println(map2.keySet());
        System.out.println(map2.values());
    }


    /**
     *
     */
    @Test
    public void test6() {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 6.19));
        pairArrayList.add(new Pair<>("version", 10.24));
        pairArrayList.add(new Pair<>("version", 13.14));
        Map<String, Double> map = pairArrayList.stream().collect(
// 生成的 map 集合中只有一个键值对：{version=13.14}
                Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));
        System.out.println(map);
    }
}
