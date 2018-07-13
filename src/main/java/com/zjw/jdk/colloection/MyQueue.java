package com.zjw.jdk.colloection;

import java.util.*;

class MyQueue {
    private LinkedList link;

    public MyQueue() {
        link = new LinkedList();
    }

    //入队
    public void inQueue(Object obj) {
        link.addFirst(obj);
    }

    //出队
    public Object deQueue() {
        return link.removeLast();
    }

    //判断队列是否为空
    public boolean isNull() {
        return link.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();

        myQueue.inQueue("java01");
        myQueue.inQueue("java02");
        myQueue.inQueue("java03");

        while (!myQueue.isNull()) {
            System.out.println(myQueue.deQueue());
        }
    }
}
