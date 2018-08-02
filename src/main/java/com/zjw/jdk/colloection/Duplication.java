package com.zjw.jdk.colloection;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by zhoum on 2018/8/2.
 * 去重复
 */
public class Duplication {

    List<BB> bbList = new ArrayList<>();

    @Before
    public void aassembly() {
        bbList.add(new BB("zjw", 10));
        bbList.add(new BB("zjw2", 10));
        bbList.add(new BB("zjw", 10));
        bbList.add(new BB("zjw2", 10));
        bbList.add(new BB("zjw", 10));
        bbList.add(new BB("zjw3", 10));
        bbList.add(new BB("zjw5", 10));
        bbList.add(new BB("zjw2", 10));
        bbList.add(new BB("zjw5", 10));
        bbList.add(new BB("zjw4", 10));
        bbList.add(new BB("zjw5", 10));
    }

    /**
     * 循环去重复
     */
    @Test
    public void test() {
        System.out.println("去重前:" + JSON.toJSONString(bbList));
        for (int i = 0; i < bbList.size(); i++) {
            for (int j = bbList.size() - 1; j > i; j--) {
                BB bb = bbList.get(i);
                BB bb2 = bbList.get(j);
                if (Objects.equals(bb.getName(), bb2.getName())) {
                    bbList.remove(j);
                }
            }
        }
        System.out.println("去重后:" + JSON.toJSONString(bbList));
    }

    /**
     * 循环去重复
     */
    @Test
    public void test2() {
        System.out.println("去重前:" + JSON.toJSONString(bbList));
        for (int i = 0; i < bbList.size(); i++) {
            BB bb = bbList.get(i);
            for (int j = 0; j < bbList.size(); j++) {
                if (j != i) {
                    BB bb2 = bbList.get(j);
                    if (Objects.equals(bb.getName(), bb2.getName())) {
                        bbList.remove(j);
                        j--;
                    }
                }
            }
        }
        System.out.println("去重后:" + JSON.toJSONString(bbList));
    }

    /**
     * HashSet去重
     */
    @Test
    public void test3() {
        System.out.println("去重前:" + JSON.toJSONString(bbList));
        HashSet<BB> bbSet = new HashSet<>();
        bbSet.addAll(bbList);
        System.out.println("去重后:" + JSON.toJSONString(bbSet));
    }

    /**
     * TreeSet去重
     */
    @Test
    public void test4() {
        System.out.println("去重前:" + JSON.toJSONString(bbList));
        TreeSet<BB> bbSet = new TreeSet<>();
        bbSet.addAll(bbList);
        System.out.println("去重后:" + JSON.toJSONString(bbSet));
    }


    @Data
    private class BB implements Comparable<BB> {
        private String name;

        private Integer age;

        public BB(String name, Integer age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BB bb = (BB) o;

            return name != null ? name.equals(bb.name) : bb.name == null;
        }

        @Override
        public int hashCode() {
            int result = 31 * (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public int compareTo(BB bb) {
            return this.name.compareTo(bb.name);
        }
    }
}
