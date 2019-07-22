package com.zjw.jdk.design.Adapter;

/**
 * Created by zhoum on 2019-07-22.
 */
public class ThinkPad implements Computer {

    @Override
    public String readCard(TFCard tfCard) {
        return tfCard.read();
    }
}
