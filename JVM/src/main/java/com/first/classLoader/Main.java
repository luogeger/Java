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

        //  getClassLoader().getClass().getClassLoader() --> null
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(Main.class.getClassLoader().getClass().getClassLoader());

        System.out.println(new MyClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    /**
     * ClassLoader的加载范围
     */
    @Test
    public void main2 () {
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));

        System.out.println("--------------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));

        System.out.println("--------------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void main3 () throws Exception{

        ClassLoader loader = new MyClassLoader();
        Class clazz = loader.loadClass("com.first.entity.Hello");
        Class clazz1 = loader.loadClass("com.first.entity.Hello");

        //  已经加载过，就不会再加载
        System.out.println(clazz == clazz1);//  true

        Hello hello = (Hello)clazz.newInstance();
        hello.main();

        System.out.println(loader.getParent());                     //  sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader.getClass().getClassLoader());     //  sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(MyClassLoader.getSystemClassLoader());   //  sun.misc.Launcher$AppClassLoader@18b4aac2
    }


    @Test
    public void main4 () {
        System.out.println(Main.class.getClassLoader());
        System.out.println(Main.class.getClassLoader().getClass().getClassLoader());
        System.out.println(Main.class.getClassLoader().getParent());//  sun.misc.Launcher$ExtClassLoader
        //  ExtClassLoader没有父加载器 ？
        System.out.println(Main.class.getClassLoader().getParent().getParent());
    }


    /**
     *
     */
    @Test
    public void main5 () {

    }
}
