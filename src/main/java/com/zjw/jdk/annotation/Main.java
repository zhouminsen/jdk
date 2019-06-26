package com.zjw.jdk.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoum on 2019-06-26.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Demo> demoTestClass = Demo.class;
        Demo demo = demoTestClass.newInstance();
        //获取该类所有的声明的方法
        Method[] declaredMethods = demoTestClass.getDeclaredMethods();
        List<Method> beforeList = new ArrayList<>();
        List<Method> runningList = new ArrayList<>();
        List<Method> afterList = new ArrayList<>();
        for (Method item : declaredMethods) {
            if (item.isAnnotationPresent(Before.class)) {
                beforeList.add(item);
            }
            if (item.isAnnotationPresent(Test.class)) {
                runningList.add(item);
            }
            if (item.isAnnotationPresent(After.class)) {
                afterList.add(item);
            }
        }
        for (Method item : runningList) {
            //调用前置方法
            for (Method e : beforeList) {
                e.invoke(demo, null);
            }
            //调用test方法
            item.invoke(demo, null);
            //调用后置方法
            for (Method e : afterList) {
                e.invoke(demo, null);
            }
            System.out.println("分界线-----------------------");
        }
        Student student = new Student();
        student.setAge(11 + "");
        student.setName("伟哥");
        getByReflect(student);
    }

    static void getByReflect(Object e) throws IllegalAccessException, InvocationTargetException {
        Class<?> aClass = e.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field item : declaredFields) {
            item.setAccessible(true);
//            item.set(e,"haha");
            Object o = item.get(e);
            System.out.println("是什么？：" + o);
        }
        Method[] declaredMethods = aClass.getDeclaredMethods();
      /*  //设置值
        for (int i = 0; i < declaredMethods.length; i++) {
            Method item = declaredMethods[i];
            if (item.getName().startsWith("set")) {
                Object invoke = item.invoke(e, i + "");
            }
        }*/

        for (int i = 0; i < declaredMethods.length; i++) {
            Method item = declaredMethods[i];
            if (item.getName().startsWith("get")) {
                Object invoke = item.invoke(e, null);
                System.out.println("是什么？：" + invoke);
            }
        }

        System.out.println(e);
    }


}
