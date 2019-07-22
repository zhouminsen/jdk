package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class ColorFactory implements AbstractFactory {


    @Override
    public Shape getShape(String name) {
        return null;
    }

    public Color getColor(String name) {
        if ("red".equals(name)) {
            return new Red();
        } else if ("blue".equals(name)) {
            return new Blue();
        }
        return null;
    }
}
