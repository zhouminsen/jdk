package com.zjw.jdk.design.singleton;

import lombok.Data;

/**
 * Created by Administrator on 2018/8/27.
 */
@Data
public class Person2 {
    private static Person2 person2 = new Person2();

    private Person2() {
    }

    public static Person2 getInstance() {
        return person2;
    }
}
