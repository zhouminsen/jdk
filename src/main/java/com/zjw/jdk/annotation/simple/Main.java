package com.zjw.jdk.annotation.simple;

import com.zjw.jdk.util.UtilFuns;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by Administrator on 2019-08-04.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> aClass = Class.forName("com.zjw.jdk.annotation.simple.Zjw");
        Object zjw = aClass.newInstance();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field item : declaredFields) {
            if (item.isAnnotationPresent(Simple.class)) {
                item.setAccessible(true);
                Simple annotation = item.getAnnotation(Simple.class);
                System.out.println("注解name：" + annotation.name());
                System.out.println("注解value：" + annotation.value());
                if (item.getName().equals("name")) {
                    item.set(zjw, annotation.name());
                } else if (item.getName().equals("action")) {
                    item.set(zjw, annotation.value());
                } else if (item.getName().equals("birthday")) {
                    String value = annotation.value();
                    Date date = UtilFuns.parseTime(value, "yyyy-MM-dd");
                    item.set(zjw, date);
                }
            }
        }
        System.out.println(zjw);
    }
}

