package com.zjw.jdk.test.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhoum on 2018/1/5.
 */
public class FileTest {
    @Test
    public void deleteOnExit() throws IOException {
        File file = File.createTempFile("HelloFile", ".txt");
        System.out.println(file.getName());
        if (!file.exists()) {
//            file.deleteOnExit();
        } else {
            System.out.println("不存在该文件");
        }
    }


    @Test
    public void delete() throws IOException {
        File file = new File("HelloFile.txt");
        if (!file.exists()) {
            file.delete();
            file.createNewFile();
        } else {

            System.out.println("不存在该文件");
        }
    }
}
