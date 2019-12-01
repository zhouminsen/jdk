package com.zjw.jdk.xml;

import com.alibaba.fastjson.JSON;
import com.zjw.jdk.util.XMLUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Steven
 */
public class Test {
    public static void main(String[] args) {
        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);
        List<Computer> list = new ArrayList<Computer>();
        list.add(new Computer("xxxMMeedd", "asus", new Date(), 4455.5, Arrays.asList(1, 2, 3, 45)));
        list.add(new Computer("lenvoXx", "lenvo", new Date(), 4999, Arrays.asList(1, 2, 3, 45)));
        user.setComputer(new Computer("lenvoXx", "lenvo", new Date(), 4999, Arrays.asList(1, 2, 3, 45)));
        user.setComputers(list);
        user.setSnList(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(JSON.toJSONString(user));
        String path = "D:\\user.xml";
        System.out.println("---将对象转换成File类型的xml Start---");
        XMLUtil.convertToXml(user, path);
        System.out.println("---将对象转换成File类型的xml End---");
        System.out.println();
        System.out.println("---将File类型的xml转换成对象 Start---");
        User user2 = XMLUtil.convertXmlFileToObject(User.class, path);
        System.out.println(user2);
        System.out.println("---将File类型的xml转换成对象 End---");
    }
}