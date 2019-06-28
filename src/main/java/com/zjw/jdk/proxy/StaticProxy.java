package com.zjw.jdk.proxy;

/**
 * Created by zhoum on 2019-06-27.
 */
public class StaticProxy {
    private CalculatorImpl target;


    public StaticProxy(CalculatorImpl calculator) {
        this.target = calculator;
    }

    public int add(int a, int b) {
        System.out.println("add method to start");
        int i = target.add(a, b);
        System.out.println("add method to end");
        return i;
    }

    public int subtract(int a, int b) {
        System.out.println("subtract method to start");
        int i = target.subtract(a, b);
        System.out.println("subtract method to end");
        return i;
    }


    public int divide(int a, int b) {
        System.out.println("divide method to start");
        int i = target.subtract(a, b);
        System.out.println("divide method to end");
        return i;
    }


    public int multiply(int a, int b) {
        System.out.println("multiply method to start");
        int i = target.subtract(a, b);
        System.out.println("multiply method to end");
        return i;
    }
}
