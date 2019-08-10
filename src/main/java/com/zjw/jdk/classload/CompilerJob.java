package com.zjw.jdk.classload;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompilerJob {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, FileNotFoundException {

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        InputStream in = new FileInputStream("D:\\AlTest.java");
        OutputStream out = new FileOutputStream(System.getProperty("user.dir") + "\\target\\classes\\AlTest.class");
        int run = javac.run(in, out, null,"-d");
        if (run != 0) {
            System.out.println("失败了");
        }
        //说明ALTet被加载了
        Class<?> alTest = Class.forName("AlTest");
        Object o = alTest.newInstance();
        Method sayHello = alTest.getMethod("sayHello");
        Object invoke = sayHello.invoke(o);
        System.out.println("调用动态加载的类的方法：" + invoke);
    }

}