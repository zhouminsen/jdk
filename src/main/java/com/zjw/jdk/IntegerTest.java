package com.zjw.jdk;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoum on 2018/5/14.
 */
public class IntegerTest {

    final String aa = "dsdsa";

    public static void main(String[] args) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        int a2 = a;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        for (int i = 0; i < 100000000; i++) {
//            System.out.println(1);
        }

        System.out.println(Integer.MAX_VALUE);

        final String aaa = "abc";
        System.out.println(aaa);

        System.out.println(new IntegerTest().aa);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Integer> list2 = new ArrayList<>();
        list = list2;
    }

   /* static  String bb(List<Integer> age) {
        return "";
    }
    static  int bb(List<Integer> age) {
        return 1;
    }*/

    @org.junit.Test
    public void test() throws ParseException {
        DecimalFormat df = new DecimalFormat();
        Number num = df.parse("5000.12");
        System.out.println(num);

    }



    @org.junit.Test
    public void test2() throws ParseException {
        System.out.println(112 % 13);
        System.out.println(Math.round(-11.5));

    }


}
