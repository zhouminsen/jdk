package com.zjw.jdk.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by zhoum on 2018/4/8.
 */
public class URLDcoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        将普通字符串转换成application/x-www-form-urlencoded字符串
        String encodeStr = URLEncoder.encode("周家伟来啦", "utf-8");
        System.out.println(encodeStr);
        //将application/x-www-form-urlencoded字符串转换为普通字符串
        String decodeStr = URLDecoder.decode(encodeStr, "utf-8");
        System.out.println(decodeStr);
    }
}

