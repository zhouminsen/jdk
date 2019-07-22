package com.zjw.jdk.design.singleton;

import lombok.Data;

/**
 * Created by Administrator on 2018/8/27.
 */
@Data
public class Person3 {

    private volatile static Person3 person3;

    private Person3() {
    }

    public static Person3 getInstance() {
        if (person3 == null) {
            synchronized (Person3.class) {
                if (person3 == null) {
                    person3 = new Person3();
                }
            }
        }
        return person3;
    }
}
