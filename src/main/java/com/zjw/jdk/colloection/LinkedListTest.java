package com.zjw.jdk.colloection;

import org.junit.Test;

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
}
