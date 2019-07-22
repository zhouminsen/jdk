package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class ProducerFactory {
    public static AbstractFactory getAbstractFactory(String name) {
        if ("color".equals(name)) {
            return new ColorFactory();
        } else if ("shape".equals(name)) {
            return new ShapeFactory();
        }
        return null;
    }
}
