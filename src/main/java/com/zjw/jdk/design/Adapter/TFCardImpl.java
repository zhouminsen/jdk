package com.zjw.jdk.design.Adapter;

/**
 * Created by zhoum on 2019-07-22.
 */
public class TFCardImpl implements TFCard {
    @Override
    public String read() {
        return "篮球，rap，唱歌。";
    }

    @Override
    public String write(String msg) {
        return null;
    }
}
