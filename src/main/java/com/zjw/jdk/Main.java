package com.zjw.jdk;

import com.zjw.jdk.xml.convert.utils.JSONObject;
import com.zjw.jdk.xml.convert.utils.XML;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoum on 2019-07-12.
 */
public class Main {
    public static void main(String[] args) {
        Long[] arr = new Long[10000000];

        Integer i = 10000;

        Double d = 10D;
        Double d2 = 10D;
        System.out.println(d == d2);
        Map<String, Object> bb = new LinkedHashMap();
        bb.put("111", 222);
        bb.put("112", 222);
        System.out.println(XML.toString(new JSONObject(bb)));
    }
}
