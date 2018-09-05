package com.zjw.jdk.design.singleton;

/**
 * Created by Administrator on 2018/8/27.
 */
public class Test {
    public static void main(String[] args) {
        Person person = Person.getInstance();
        System.out.println(person);
    }
}
