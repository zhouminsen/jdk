package com.zjw.jdk.design.singleton;

/**
 * Created by Administrator on 2018/8/27.
 */
public class Main {
    public static void main(String[] args) {
        Person person = Person.getInstance();
        System.out.println(person);

        Person2 person2 = Person2.getInstance();
        System.out.println(person2);

        Person3 person3 = Person3.getInstance();
        System.out.println(person3);
    }
}
