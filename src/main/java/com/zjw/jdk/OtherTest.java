package com.zjw.jdk;

import com.zjw.jdk.annotation.Student;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void test6() {
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("伟哥");
        student.setAge("1");
        Student student2 = new Student();
        student2.setName("伟哥2");
        student2.setAge("1");
        student2.setAge("1");
        Student student3 = new Student();
        student3.setName("伟哥");
        student3.setAge("1");
        Student student4 = new Student();
        student4.setName("伟哥2");
        student4.setAge("1");
        Student student5 = new Student();
        student5.setName("伟哥");
        student5.setAge("1");
        Student student6 = new Student();
        student6.setName("伟哥2");
        student6.setAge("1");
        Student student7 = new Student();
        student7.setName("伟哥");
        student7.setAge("1");
        Student student8 = new Student();
        student8.setName("伟哥2");
        student8.setAge("1");
        Student student9 = new Student();
        student9.setName("伟哥2");
        student9.setAge("1");
        list.add(student);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student9);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        /*for (int i = 0; i < list.size(); i++) {
            Student item = list.get(i);
            for (int i1 = 0; i1 < list.size(); i1++) {
                Student e = list.get(i1);
                if (item != e && item.getName().equals(e.getName())) {
                    item.setAge(item.getAge() + "," + e.getAge());
                    list.remove(i1);
                    i1--;
                }
            }
        }*/
        for (int i = 0; i < list.size(); i++) {
            Student item = list.get(i);
            for (int i1 = list.size() - 1; i1 >= 0; i1--) {
                Student e = list.get(i1);
                if (item != e && item.getName().equals(e.getName())) {
                    item.setAge(item.getAge() + "," + e.getAge());
                    list.remove(i1);
                }
            }
        }

        System.out.println(list);

    }


}
