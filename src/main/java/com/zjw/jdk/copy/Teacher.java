package com.zjw.jdk.copy;

import java.io.Serializable;

/**
 * Created by zhoum on 2019-07-22.
 */
public class Teacher implements Cloneable,Serializable {

    String name;

    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Teacher teacher = (Teacher) super.clone();
        Student student = (Student) this.student.clone();
        teacher.setStudent(student);
        return teacher;
    }
}
