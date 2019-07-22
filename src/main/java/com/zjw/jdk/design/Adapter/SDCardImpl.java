package com.zjw.jdk.design.Adapter;

/**
 * Created by zhoum on 2019-07-22.
 */
public class SDCardImpl implements SDCard {
    @Override
    public String read() {
        return "大家好：我是时长2年半的个人练习生蔡徐坤";
    }

    @Override
    public String write(String msg) {
        return null;
    }
}


