package com.zjw.jdk;


import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2019-08-04.
 */
public class Encode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "周家伟";
        byte[] utf8 = str.getBytes("utf-8");
        String s = new String(utf8, "iso8859-1");
        System.out.println(s);
        String s1 = new String(s.getBytes("iso8859-1"), "utf-8");
        System.out.println(s1);
    }
}
