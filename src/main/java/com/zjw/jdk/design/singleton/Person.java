package com.zjw.jdk.design.singleton;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/8/27.
 */
@Getter
@Setter
public class Person {
    private String name;

    private Integer age;

    private Person() {
    }

    public static Person getInstance() {
        return Singleton.person;
    }

    private static class Singleton {
        private static Person person = new Person();

    }
}
