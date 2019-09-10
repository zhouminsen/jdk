package com.zjw.jdk.classload;

/**
 * Created by Administrator on 2019-08-03.
 */
public class FinalLoading {

    final String s = "zjw";
    String s2 = "wjz";

    public static void main(String[] args) {
        FinalLoading finalLoading = new FinalLoading();
        String sss = finalLoading.s + finalLoading.s2;
        String ssss = finalLoading.s2 + finalLoading.s;
        System.out.println("什么");
    }
}
