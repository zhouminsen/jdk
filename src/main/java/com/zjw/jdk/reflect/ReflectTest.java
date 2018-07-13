package com.zjw.jdk.reflect;

/**
 * Created by zhoum on 2018/7/6.
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Integer> integerClass = int.class;
        System.out.println(integerClass);
        Class<Integer> integerClass1 = Integer.class;
        System.out.println(integerClass1);

        Class<?> aClass = Class.forName("java.lang.Boolean");
        System.out.println(aClass);

        Class<Boolean> type = Boolean.TYPE;
        System.out.println(type);
    }
}
