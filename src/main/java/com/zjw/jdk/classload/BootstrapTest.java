package com.zjw.jdk.classload;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by zhoum on 2018/4/12.
 */
public class BootstrapTest {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL item : urLs) {
            System.out.println(item.toExternalForm());
        }
    }
}
