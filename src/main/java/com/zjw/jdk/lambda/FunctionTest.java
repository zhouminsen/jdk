package com.zjw.jdk.lambda;


import org.junit.Test;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by zhoum on 2018/3/14.
 */
public class FunctionTest {
    @Test
    public void test() {
        Consumer tConsumer = System.out::println;
        tConsumer.accept("aaaa");
    }


    @Test
    public void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime);
        Function<Integer, Integer> doubleToIntFunction = i -> i + i;
        System.out.println(doubleToIntFunction.apply(2));
    }

}