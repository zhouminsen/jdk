package com.zjw.jdk.colloection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhoum on 2018/7/9.
 */
public class ArrayListTest {
    @Test
    public void test() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 15; i++) {
            list.add(i+"");
        }
        Object collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);

    }

    @Test
    public void test2() {
        int[] arr = {2, 3, 1, 4, 6, 8};
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test3() {
        int[] arr = {2, 3, 1, 4, 6};
        int[] tempArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            tempArr[arr.length - i - 1] = arr[i];
        }
        System.out.println(Arrays.toString(tempArr));
    }

    @Test
    public void test4() {
        int[] arr = {2, 3, 1, 4, 6};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int current = arr[j];
                int next = arr[j + 1];
                if (current < next) {
                    arr[j + 1] = current;
                    arr[j] = next;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void test5() {
        int answer = 0;
        int key = 4;
        int[] arr = {2, 3, 1, 4, 6};
        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int i = (left + right) / 2;
            int v = arr[i];
            if (v == key) {
                answer = i;
                break;
            } else if (v > key) {
                right = i - 1;
            } else {
                left = i + 1;
            }

        }
        System.out.println(answer);
    }

    @Test
    public void test6() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(list);
        System.out.println("前：" + list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println("后：" + list);
        System.out.println(list2);
    }
}
