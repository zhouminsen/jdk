package com.zjw.jdk.proxy;

/**
 * Created by zhoum on 2019-06-27.
 */
public class CalculatorImpl implements Calculator, Calculator2 {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}
