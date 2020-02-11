package com.first.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author luoxiaoqing
 * @date 2020-02-11__16:59
 */
public class ExceptionTest {

    public static void main(String[] args) {
        Object o = main1();
        //  java.lang.RuntimeException: java.io.FileNotFoundException: C:\test.text (系统找不到指定的文件。)
        System.out.println(o.toString());
    }

    public static Object main1 () {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("C://test.text");
        } catch (FileNotFoundException e) {
            //  C:\test.text (系统找不到指定的文件。)
            System.out.println(e.getMessage());
            return new RuntimeException(e);
        }

        return fileInputStream;
    }
}
