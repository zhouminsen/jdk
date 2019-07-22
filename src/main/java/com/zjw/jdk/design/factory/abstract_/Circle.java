package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("是 circle");
    }
}
