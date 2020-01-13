package com.company.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceControlSubjectProxy implements ISubject {
    private static final Log logger = LogFactory.getLog(ServiceControlSubjectProxy.class);
    private ISubject subject;

//    public ServiceControlSubjectProxy(ISubject subject) {
//        this.subject = subject;
//    }

    @Override
    public String request() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return null;
    }

    @Test
    public void test() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间 == 2018-03-05 21:50:58

    }
}
