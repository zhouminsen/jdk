package com.zjw.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhoum on 2018/6/27.
 */
public class OtherTest {
    @Test
    public void test() {
        Double a = 1.0, b = 0.0;
        System.out.println(a / b);

        System.out.println(5 % 3);
    }

    @Test
    public void test2() {
        List<Integer> source = Arrays.asList(1);
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> split = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < source.size(); i++) {
            Integer item = source.get(i);
            count++;
            if (count % 4 != 0) {
                split.add(item);
                if (split.size() == 3) {
                    lists.add(split);
                    split = new ArrayList<>();
                } else if (source.size() - i <= 1) {
                    lists.add(split);
                }
            } else {
                count = 1;
                split.add(item);
                if (source.size() - i == 1) {
                    lists.add(split);
                }
            }
        }
        System.out.println(lists);

    }

    @Test
    public void test3() {
        ArrayList<Integer> source = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            source.add(i + 1);
        }
        System.out.println(source);
        List<List<Integer>> lists = new ArrayList<>();
        int limit = 3;
        int split = source.size() / limit;
        for (int i = 0; i < split; i++) {
            lists.add(source.subList(i * limit, limit * (i + 1)));

        }
        List<Integer> lastList = source.subList(limit * split, source.size());
        if (!lastList.isEmpty()) {
            lists.add(lastList);
        }
        System.out.println(lists);

    }

    @Test
    public void test4() {
        Integer i = null;
//        System.out.println(i == 0);
        System.out.println(Objects.equals(i, 0));
    }

    @Test
    public void test5() {
        int i = 4;
        if (!Objects.equals(i, 3) && !Objects.equals(i, 4)) {
            System.out.println("haah");
        }
    }


}
