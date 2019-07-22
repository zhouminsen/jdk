package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class Main {
    public static void main(String[] args) {
        //获取形状抽象工厂
        AbstractFactory shapeFactory = ProducerFactory.getAbstractFactory("shape");
        //获取具体形状接口
        Shape shape = shapeFactory.getShape("square");
        shape.draw();

        //获取颜色抽象工厂
        AbstractFactory colorFactory = ProducerFactory.getAbstractFactory("color");
        //获取具体颜色接口
        Color red = colorFactory.getColor("red");
        red.fill();
    }
}
