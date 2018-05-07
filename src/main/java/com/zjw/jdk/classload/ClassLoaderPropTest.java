package com.zjw.jdk.classload;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by zhoum on 2018/4/13.
 */
public class ClassLoaderPropTest {

    public static void main(String[] args) throws IOException {
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器:" + systemLoader);
        Enumeration<URL> resources = systemLoader.getResources("");
        while (resources.hasMoreElements()) {
            System.out.println(resources.nextElement());
        }
        //获取系统类加载器的父类加载器,得到扩展类加载器
        ClassLoader extensionLader = systemLoader.getParent();
        System.out.println("扩展类加载器:" + extensionLader);
        System.out.println("扩展类加载器的加载路径: " + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载的parent: "+extensionLader.getParent());

    }
}
