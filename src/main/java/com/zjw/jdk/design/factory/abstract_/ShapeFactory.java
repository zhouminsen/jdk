package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class ShapeFactory implements AbstractFactory {
    @Override
    public Color getColor(String name) {
        return null;
    }

    public Shape getShape(String name) {
        if ("circle".equals(name)) {
            return new Circle();
        } else if ("square".equals(name)) {
            return new Square();
        }
        return null;
    }
}
