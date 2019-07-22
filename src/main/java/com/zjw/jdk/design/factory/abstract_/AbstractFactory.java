package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public interface AbstractFactory {

    Color getColor(String name);


    Shape getShape(String name);

}
