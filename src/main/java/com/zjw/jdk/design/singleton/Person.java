package com.zjw.jdk.design.singleton;

import lombok.Data;

/**
 * Created by Administrator on 2018/8/27.
 */
@Data
public class Person {
    private String name;
    private Integer age;

    private Person() {

    }

    public static Person getInstance() {
        return personInstance.person;
    }

    private static class personInstance {
        private static Person person = new Person();
    }


}
