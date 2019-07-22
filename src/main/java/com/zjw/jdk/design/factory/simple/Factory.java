package com.zjw.jdk.design.factory.simple;

/**
 * Created by zhoum on 2019-07-19.
 */
public class Factory {
    Shape getShape(String name) {
        if ("circle".equals(name)) {
            return new Circle();
        } else if ("square".equals(name)) {
            return new Square();
        }
        return null;
    }
}
