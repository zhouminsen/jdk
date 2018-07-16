package com.zjw.jdk.colloection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhoum on 2018/5/3.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.add(-1);
        List aa = new ArrayList();
        aa.add(1);
        aa.add(2);
        aa.add(3);
        aa.add(4);
        linkedList.addAll(aa);
        linkedList.contains(null);
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(linkedList);
      /*  linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);*/
        /*linkedList.remove();
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.getLast());*/

    }
}
