package com.first;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author luoxiaoqing
 * @date 2020-02-05__03:06
 */
public class QuickTest {

    /**
     * 获取类名和全类名
     */
    @Test
    public void main1 () {
        Class<QuickTest> quickTestClass = QuickTest.class;
        System.out.println(quickTestClass);
        System.out.println(quickTestClass);

        System.out.println("image".toLowerCase());

    }


    /**
     * 读取resource目录下的文件
     *
     * @throws IOException
     */
    @Test
    public void main2 () throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resource/map.json"));
        String json = new String(bytes);

    }


}
