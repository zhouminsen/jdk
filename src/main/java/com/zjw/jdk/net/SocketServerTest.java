package com.zjw.jdk.net;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhoum on 2018/4/8.
 */
public class SocketServerTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(30000);
        while (true) {
            //每当接受到客户端socket的请求时,服务器端也对应产生一个socket
            Socket s = serverSocket.accept();
//            Thread.sleep(2000);
            PrintStream ps = new PrintStream(s.getOutputStream());
            System.out.println("我来了");
            ps.print("您好,您收到了服务器的新年祝福");
            ps.close();
            s.close();
        }
    }
}

 class SocketClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(30000), 1000);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println("来自客户端的数据:" + line);
        br.close();
        socket.close();
    }

}




