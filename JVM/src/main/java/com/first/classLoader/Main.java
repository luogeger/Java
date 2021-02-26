package com.first.classLoader;

import com.first.entity.Hello;
import org.junit.Test;

public class Main {

    /**
     * ClassLoader的级别
     */
    @Test
    public void main1 () {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(Main.class.getClassLoader());

        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(Main.class.getClassLoader().getClass().getClassLoader());

        System.out.println(new ClassLoaderPlus().getParent());
        System.out.println(ClassLoader.getSystemClassLoader());
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void main2 () throws Exception{
        ClassLoader loader = new ClassLoaderPlus();
        Class clazz = loader.loadClass("com.first.jvm.entity.Hello");
        Class clazz1 = loader.loadClass("com.first.jvm.entity.Hello");

        System.out.println(clazz == clazz1);

        Hello hello = (Hello)clazz.newInstance();
        hello.main();

        System.out.println(loader.getClass().getClassLoader());
        System.out.println(loader.getParent());
        System.out.println(ClassLoaderPlus.getSystemClassLoader());
    }
}
