package com.zjw.jdk.net;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by zhoum on 2018/4/8.
 */
public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        //根据主机名获取对应的InetAddress实列
        InetAddress ip = InetAddress.getByName("www.crazyit.org");
        System.out.println("carzyit是否可达:" + ip.isReachable(2000));
        //获取该InetAddress实列的ip地址字符串
        System.out.println(ip.getHostAddress());
        //根据原始ip地址;奥获取对应的InetAddress实列
        InetAddress local = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        System.out.println("本地是否可达:" + local.isReachable(5000));
        //获取该InetAddress实列对应的全限定域名
        System.out.println(local.getCanonicalHostName());
    }
}
