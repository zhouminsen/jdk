package com.zjw.jdk.design.Adapter;

/**
 * Created by zhoum on 2019-07-22.
 */
public class SDAdapterTF implements TFCard {
    private SDCard sdCard;

    public SDAdapterTF(SDCard sdCard) {
        this.sdCard = sdCard;
    }

    @Override
    public String read() {
        return sdCard.read();
    }

    @Override
    public String write(String msg) {
        return null;
    }
}
