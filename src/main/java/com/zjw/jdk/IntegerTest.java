package com.zjw.jdk;

/**
 * Created by zhoum on 2018/5/14.
 */
public class IntegerTest {

    final String aa = "dsdsa";

    public static void main(String[] args) {

        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;
        Long g=3L;
        int a2 = a;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(a + b));
        for (int i = 0; i < 100000000; i++) {
//            System.out.println(1);
        }

        System.out.println(Integer.MAX_VALUE);

        final String aaa = "abc";
        System.out.println(aaa);

        System.out.println(new IntegerTest().aa);

    }

   /* static  String bb(List<Integer> age) {
        return "";
    }
    static  int bb(List<Integer> age) {
        return 1;
    }*/
}
