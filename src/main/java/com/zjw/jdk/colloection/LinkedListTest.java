package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zhoum on 2018/5/3.
 */
public class LinkedListTest {

    /**
     * add和addAll,迭代全过程
     */
    @Test
    public void test() {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.add(-1);
        linkedList.add(10);
        linkedList.add(5);

        LinkedList aa = new LinkedList();
        aa.add(20);
        aa.add(60);
        aa.add(40);
        linkedList.addAll(aa);
        linkedList.contains(null);
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(linkedList);
    }

    /**
     * 队列
     * 先进先出
     */
    @Test
    public void test2() {
        LinkedList queue = new LinkedList();
        //入队
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("现在的队列是:" + queue);
        System.out.println("开始出队了");
        int i = 0;
        while (!queue.isEmpty()) {
            System.out.println("第" + (++i) + "个出来的是:" + queue.removeFirst());
        }
    }


    @Test
    public void test3() {
        int count = 1000000;

        LinkedList linkedList = new LinkedList();
        long l = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            linkedList.add(i);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("linkedlist:" + (l1 - l));




    }

    @Test
    public void test4() {
        int count = 1000000;

        ArrayList arrayList = new ArrayList();
        long c = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            arrayList.add(i);
        }
        long c1 = System.currentTimeMillis();
        System.out.println("arraylist:" + (c1 - c));

    }
}
