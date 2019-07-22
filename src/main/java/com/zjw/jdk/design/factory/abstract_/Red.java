package com.zjw.jdk.design.factory.abstract_;

/**
 * Created by zhoum on 2019-07-19.
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("是 red");
    }
}
