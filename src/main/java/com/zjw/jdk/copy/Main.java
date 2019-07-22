package com.zjw.jdk.copy;

import com.alibaba.fastjson.JSON;
import com.zjw.jdk.util.UtilFuns;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoum on 2019-07-22.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        method();
        method2();
        method3();

    }

    public static void method() throws CloneNotSupportedException {
        Teacher teacher = new Teacher();
        teacher.setName("周老师");
        Student student = new Student();
        student.setName("伟哥");
        teacher.setStudent(student);
        System.out.println("打印原始对象");
        System.out.println(teacher);
        System.out.println(teacher.getStudent());
        //clone对象
        Teacher teacher2 = (Teacher) teacher.clone();
        System.out.println(" 打印clone后的对象");
        System.out.println(teacher2);
        System.out.println(teacher2.getStudent());

    }


    public static void method2() {
        Teacher teacher = new Teacher();
        teacher.setName("周老师");
        Student student = new Student();
        student.setName("伟哥");
        teacher.setStudent(student);
        System.out.println("打印原始对象");
        System.out.println(teacher);
        System.out.println(teacher.getStudent());
        //json序列化后的对象
        Teacher teacher2 = JSON.parseObject(JSON.toJSONString(teacher), Teacher.class);
        System.out.println(" 打印json序列化后的对象");
        System.out.println(teacher2);
        System.out.println(teacher2.getStudent());
    }

    public static void method3() throws IOException, ClassNotFoundException {
        Teacher teacher = new Teacher();
        teacher.setName("周老师");
        Student student = new Student();
        student.setName("伟哥");
        teacher.setStudent(student);
        System.out.println("打印原始对象");
        System.out.println(teacher);
        System.out.println(teacher.getStudent());
        //byte序列化后的对象
        List<Teacher> teachers = UtilFuns.deepCopy(Arrays.asList(teacher));
        Teacher teacher2 = teachers.get(0);
        System.out.println(" 打印byte序列化后的对象");
        System.out.println(teacher2);
        System.out.println(teacher2.getStudent());
    }
}
