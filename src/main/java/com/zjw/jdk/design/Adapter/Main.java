package com.zjw.jdk.design.Adapter;

/**
 * Created by zhoum on 2019-07-22.
 * https://www.runoob.com/design-pattern/adapter-pattern.html
 */

public class Main {
    public static void main(String[] args) {
        Computer computer = new ThinkPad();
        TFCard tfCard = new TFCardImpl();
        String s = computer.readCard(tfCard);
        System.out.println("读取tf的内容：" + s);

        //适配sd卡
        SDAdapterTF sdAdapterTF = new SDAdapterTF(new SDCardImpl());
        String s2 = computer.readCard(sdAdapterTF);
        System.out.println("读取sd卡的内容：" + s2);

    }
}
