package com.zjw.jdk.annotation;

/**
 * Created by zhoum on 2019-06-26.
 */
public class Demo {
    @Before
    public void init() {
        System.out.println("I was created");
    }

    @Test
    public void add() {
        System.out.println("I am executing the method add");
    }

    @Test
    public void delete() {
        System.out.println("I am executing the method delete");
    }

    @After
    public void destroy() {
        System.out.println("I was destroyed");
    }
}
