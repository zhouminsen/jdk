package com.zjw.jdk.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoum on 2018/7/6.
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
     /*   Class<Integer> integerClass = int.class;
        System.out.println(integerClass);
        Class<Integer> integerClass1 = Integer.class;
        System.out.println(integerClass1);

        Class<?> aClass = Class.forName("java.lang.Boolean");
        System.out.println(aClass);

        Class<Boolean> type = Boolean.TYPE;
        System.out.println(type);*/


        Class<ArrayList> listClass = ArrayList.class;
        List<String> list = listClass.newInstance();
        Method add = listClass.getMethod("add", Object.class);
        add.invoke(list, "123");
        add.invoke(list, 1);
        add.invoke(list, Boolean.TRUE);
        System.out.println(list.size());
        System.out.println(list);
        for (String item : list) {
            System.out.println(item);
        }
    }
}
