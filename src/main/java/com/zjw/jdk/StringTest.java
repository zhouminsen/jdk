package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoum on 2018/1/9.
 */
public class StringTest {

    @Test
    public void split() {
        String s = "a,b,n,d ";
        String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));
        String[] strings = StringUtils.splitPreserveAllTokens(s, ",");
        System.out.println(strings.length);
        System.out.println(Arrays.toString(strings));
        System.out.println(77 / 2);
    }


    @Test
    public void intern() {
        String s1 = new StringBuilder("ja").append("va1").toString();
        System.out.println(s1.intern() == s1);
    }

    @Test
    public void test() {
        String a = "1";
        String a2 = "2";
        String a3 = "3";
        String s = a + a2 + a3;
        System.out.println(s);
    }

    @Test
    public void test2() {
        String aa = "{\"qrCode\":\"https://qr.alipay.com/bax07306eoxwbe02utje0039 \n" +
                "\n" +
                "\",\"transactionId\":\"0_20180711170141468\"}";
        Map<String, String> bb = new HashMap<>();
        bb.put("123456789012", aa);
        System.out.println(JSON.toJSONString(bb));
    }
}
