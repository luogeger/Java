package com.company.f_date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Date;

public class Adate {


    @Test
    public void test1 () {
        System.out.println(new Date());// Sat May 18 10:02:48 CST 2019
        System.out.println(new Date().getTime());// 1558144706160
        System.out.println(DateFormatUtils.format(new Date(), "MM-dd"));// 07-11
    }


}
