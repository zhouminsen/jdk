package com.zjw.jdk.design.strategy;

/**
 * Created by Administrator on 2018/8/25.
 */
public class Test {
    public static void main(String[] args) {
        Player player = new Player();
        player.buy(10000D);
        System.out.println(player.calLastAmount());

    }
}
