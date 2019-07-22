package com.zjw.jdk.design.factory.simple;

/**
 * Created by zhoum on 2019-07-19.
 * https://www.runoob.com/design-pattern/factory-pattern.html
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Shape shape = factory.getShape("circle");
        shape.draw();
        shape = factory.getShape("square");
        shape.draw();
    }
}
