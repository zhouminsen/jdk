package com.zjw.jdk.copy;

import java.io.Serializable;

/**
 * Created by zhoum on 2019-07-22.
 */
public class Student implements Cloneable,Serializable{


    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        return student;
    }
}
