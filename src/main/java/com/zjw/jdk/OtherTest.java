package com.zjw.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoum on 2018/6/27.
 */
public class OtherTest {
    @Test
    public void test() {
        Double a = 1.0, b = 0.0;
        System.out.println(a / b);

        System.out.println(5 %3);
    }
 @Test
    public void test2() {
     List<Integer> source = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10);
     List<List<Integer>> lists = new ArrayList<>();
     List<Integer> split = new ArrayList<>();
     int count = 0;
     for (Integer item : source) {
         count++;
         if (count % 4 != 0) {
             split.add(item);
             if (split.size() == 3) {
                 lists.add(split);
                 split = new ArrayList<>();
             }
         } else {
             count = 1;
             split.add(item);
         }
     }

     System.out.println(lists);
 }


}
