package com.first.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Adate {


    @Test
    public void test1 () {
        System.out.println(new Date());// Sat May 18 10:02:48 CST 2019
        System.out.println(new Date().getTime());// 1558144706160
        System.out.println(DateFormatUtils.format(new Date(), "MM-dd"));// 07-11
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));// 07-11
    }

    @Test
    public void test2 () {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }

    @Test
    public void test3 () {
        Calendar beforeTime = Calendar.getInstance();
        System.out.println(beforeTime.getTime());// 当前时间

        beforeTime.add(Calendar.MINUTE, -5);
        System.out.println(beforeTime.getTime());// 5分钟之前的时间

        String before5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeTime.getTime());  // 前五分钟时间--String
        System.out.println(before5);
    }

}
