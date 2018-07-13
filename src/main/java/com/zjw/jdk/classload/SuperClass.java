package com.zjw.jdk.classload;

/**
 * Created by zhoum on 2018/5/29.
 */
public class SuperClass {

    public  static  int value =1;

    public SuperClass() {
        System.out.println("haha ");
    }

    static {
        System.out.println("SuperClass init !");
    }


}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init !");
    }
}

class bb{
    public static void main(String[] args) {
        SuperClass[] aa = new SuperClass[10];
        System.out.println(SuperClass.value);
    }

}

