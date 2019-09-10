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

        String string = "05b062e4082e478c91f0bfb8a71c1887," +
                "b83a690398e5420ab2a4b15e4ad5e1d7," +
                "5a5b94cf439d45579847d9301bd28f0d," +
                "af01606ef385497da120f4b20f030c0b," +
                "0e928b29069c4918a4fae84a7acd3f91," +
                "17bdef8414684ce5b0f1d68cae3db1e9," +
                "b5180a15c4ab450ca82dd2e3ab43ce61," +
                "4ea7621bf4ef4b07b6f23f74f797ea68," +
                "07d49064967e426389e5a5e3637943d9," +
                "0651ab9acbdf451291bd632098eb6fd6," +
                "d059808c00d6411a95d8b686a0041eeb," +
                "7c2f8ab753994a85ba62e4a03b200639," +
                "3b3d86786c144113be87f3cfb8510549," +
                "6fb8fc5d5b994a04aec1ae22bed4b3ea," +
                "72d27bf9b36a4560b7b44331dd212f73," +
                "7e37c21244a34cc7be134ce2bb09e57c," +
                "cd482143393b49ddb575363f679ee45a," +
                "f45ed9e927264e32adbf6dbafb4a84bf," +
                "6d1796c5831345b188a1114af414c564," +
                "e6034cfff6d54c928e2aa8db53f0a444," +
                "9e5d11e40a2e4dc0bcb24fad117b45c5," +
                "4b05297377c54d37a2142903260dd274," +
                "cb43125978634b7ab2f752d3e1628d91," +
                "fd06addee5904fd5b3ea1168cde0dedb," +
                "2efda165d88b4589bf25c324aaa07dee," +
                "29df75ab4ee84ba6a81c1f76adbc9b3e," +
                "953652f9cf144a13b1b2b80795ce1c8d," +
                "b6d9b272eb3c4191a8b1ee951b0317c4," +
                "166817ff94434234a49754edfe18cb42," +
                "82ba2f9f14344db69e6ee5fedbb016ba," +
                "dabc31b520e142f28139f75ab3520810," +
                "73a46a48bfbe4cd4bd3fde0521405790," +
                "2ae4100696a84189b0466d27ee308494," +
                "10a4f9bad8b64fd89803f0968b63bc34," +
                "60969b980a7f497d8c3a24b21b111fac," +
                "37fe4045764447eeb8cea07349306f38," +
                "ad12e7e939334fa48d53c13c10f1f1f7," +
                "b3b5f15c69174e7188b625c03ba6624a," +
                "53263dba9c0648ee9a0fbe5dfa0b6475," +
                "a5c1ccca50b44909a48af3936c13dd27," +
                "ce4ef19f52ca461695b0f051623a1afe," +
                "e2eb13cb6a8246f5a1541ac1ccf77c07," +
                "4e00c143a5d14ee38fe725cba2dfed81," +
                "0a1bebe854584fb3bbcba566616ffcb6," +
                "5c1dbc2db79947a6a144134340f628fb," +
                "600857275acc44fba9c740b8e7251ae5," +
                "b58f339024cf404fa2b2660cd75e0eee," +
                "a26b0be9ef3c42beb55e2391aa574878," +
                "933aca6c819647e3b26a0a21244f8dc7," +
                "04bc3779c4584e8b9f811fbbe5b75282," +
                "c6dadc4a4cc54e5d9745467263eab34a," +
                "a2eeb9bf3db14686af35de5ec12797d7," +
                "4b9547c416a54e139ddc5415cc6d55a3," +
                "034e168d352147878ebb5908273f5d8e," +
                "7c62acd8fd14435380a54bc05fa3abf0," +
                "5ca35412214d4258b8a2084fed2d2ebf," +
                "7268ccb302214aa6a5e471b4f7fbb719," +
                "29bedb49c2d84d0bb8d9b9249c8107e4," +
                "b938cb5519ee4335b2535a15c3d742f2," +
                "ec3d81d4578640039f375de9ac94b7c1," +
                "3234c46b1f054a4fa7a41f0ce4d14144," +
                "bd804bb20d724afc95f3f5aafc62e979," +
                "e0943b207cec4934bec57cd150de53bb," +
                "37ea3b838eaa4011b48c3b216208651e," +
                "a5eec2c5c37e4c47bb637d16e716097e," +
                "18068d97bb4247d29f92c5e08767cb60," +
                "82e75315c1e1455c9e2765e6666e8b5e," +
                "24d0cabd28f94d62b70e5338c4ec7d02," +
                "a4617b9d785e473c90345bfd54b8e93e," +
                "caee078404de4d86af2e7aa8ffbc72fb," +
                "d6d9611f12534ec8bfd46f33eddfd4a0," +
                "afd5ad8ab7f24cc49faf8f6736177ace," +
                "b65942a7b281488b9ee28f7da1705960," +
                "e4c25af97dba42858df9d072ea9128e0," +
                "a712f742f8fb475980102712ec4f279f," +
                "42a12d639b5a4c558db5e39fdca7f96c," +
                "af0d97a4301e45b79cd70cf6a6e21b65," +
                "d60e69110487496caaedae50f2253657," +
                "1bac47ec561a48319e1cb024f2c3195e," +
                "4fb786f73d1d427f89faf3523ff0b6aa," +
                "deec4b278f734656b22515dd1c1cbc74," +
                "66974af2736249719393bf7b9b5f7aec," +
                "f4219025e5144d988fe48fd7616a08f3," +
                "80c1b4b73b904de885f0bbdf898611df," +
                "f0da92cf8f8d48baa1c24bbfca1ae21f," +
                "f87e08034c5349a7a0cdee6e5d19b596," +
                "a5e2a195cf0f447d86439c79044bde36,";
        String[] split = string.split(",");

        System.out.println(split);
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
