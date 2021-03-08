package com.first.classLoader;

import com.first.entity.Hello;
import org.junit.Test;
import sun.misc.Launcher;

public class Main {


    /**
     * AppClassLoader 加载当前类
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void main0() throws ClassNotFoundException {
        Class<?> clazz = Main.class.getClassLoader().loadClass("com.first.classLoader.Main");
        System.out.println(clazz);
        System.out.println(clazz.getName());
    }


    /**
     * ClassLoader的级别
     */
    @Test
    public void main1() {
        System.out.println(String.class.getClassLoader());
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        System.out.println(Main.class.getClassLoader());

        //  getClassLoader().getClass().getClassLoader() --> null
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(Main.class.getClassLoader().getClass().getClassLoader());

        System.out.println(new ClassLoaderFindClass().getParent());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    /**
     * ClassLoader的加载范围
     */
    @Test
    public void main2() {
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
     * 手动加载：加载一次之后，就不会再加载了
     *
     * @throws Exception
     */
    @Test
    public void main3() throws Exception {

        ClassLoader loader = new ClassLoaderFindClass();
        Class clazz = loader.loadClass("com.first.entity.Hello");
        Class clazz1 = loader.loadClass("com.first.entity.Hello");

        //  已经加载过，就不会再加载
        System.out.println(clazz == clazz1);//  true

        Hello hello = (Hello) clazz.newInstance();
        hello.main();

        System.out.println(loader.getParent());                     //  sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader.getClass().getClassLoader());     //  sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoaderFindClass.getSystemClassLoader());   //  sun.misc.Launcher$AppClassLoader@18b4aac2
    }


    /**
     * 热加载
     */
    @Test
    public void main31() {

    }


    /**
     *
     */
    @Test
    public void main4() {
        //  Main的ClassLoader是AppClassLoader
        System.out.println(Main.class.getClassLoader());

        //  null表示BootStrapClassLoader
        System.out.println(Main.class.getClassLoader().getClass().getClassLoader());//  null
        System.out.println(Launcher.class.getClassLoader());//  null

        //  sun.misc.Launcher$ExtClassLoader
        System.out.println(Main.class.getClassLoader().getParent());

        //  ExtClassLoader的parent是BootStrapClassLoader, 是C++实现的， 再Java里没有与之对应的类
        System.out.println(Main.class.getClassLoader().getParent().getParent());
    }


    /**
     * 加载Class文件，加密之后，再写回磁盘
     */
    @Test
    public void main5() {


    }


    /**
     * JVM并没有规定什么时候加载，HotSpot严格来讲是LazyLoading: 使用时加载。
     * 但是规定了什么时候初始化。
     */
    @Test
    public void main6 () throws ClassNotFoundException {

//        P p;//  没有使用，不会加载
//        System.out.println(P.f);//  final也不会加载
//        System.out.println(P.s);//  非final，会加载
        Class<?> clazz = Class.forName("com.first.classLoader.Main$P");//  会加载

    }


    /**
     * 不能热加载
     */
    @Test
    public void main7 () throws ClassNotFoundException {
        ClassLoader loader = new ClassLoaderFindClass();

        Class clazz = loader.loadClass("com.first.entity.Hello");
        System.out.println(clazz.hashCode());

        Class clazz1 = loader.loadClass("com.first.entity.Hello");
        System.out.println(clazz1.hashCode());

        System.out.println(clazz == clazz1);

    }

    /**
     * 热加载
     */
    @Test
    public void main8 () throws ClassNotFoundException {
        ClassLoader loader = new ClassLoaderLoadClass();
        Class clazz = loader.loadClass("com.first.entity.Hello");
        System.out.println(clazz.hashCode());

        //  ClassLoader要重新实例化一次, 因为同一个ClassLoader不能加载同一个类，为了实现热加载，直接把ClassLoader干掉
        loader = new ClassLoaderLoadClass();
        Class clazz1 = loader.loadClass("com.first.entity.Hello");
        System.out.println(clazz1.hashCode());

        System.out.println(clazz == clazz1);
    }





    //  inner class
    //  ================================================================================================================

    public static class P {
        public static final int f = 8;
        public static int s = 9;

        static {
            System.out.println("P");
        }
    }

    public static class C extends P {
        static {
            System.out.println("C");
        }
    }

}
