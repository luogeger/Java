package com.first.classLoader;

import java.io.*;

public class MyClassLoader extends ClassLoader{

    private final String classLoaderName;

    private String classPath;

    public MyClassLoader(String classPath, String classLoaderName) {
        // 未指定则默认使用应用类加载器
        super();
        this.classLoaderName = classLoaderName;
        this.classPath = classPath;
    }

    /**
     * --
     * @param parent
     * @param classLoaderName
     */
    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        // 显式的指定父类加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) {
        System.out.println("MyClassLoader ...");
        byte[] bytes = null;

        try {
            bytes = myLoadClass(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 自定义类加载器
     * @param name
     * @return
     */
    private byte[] myLoadClass(String name) throws Exception{
        name = name.replace(".", "/");
        File file = new File(this.classPath + name + ".class");

        /*try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             InputStream fileInputStream = new FileInputStream(file)
        ) {
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(ch);
            }
            return byteArrayOutputStream.toByteArray();
        }*/
        return null;
    }
}
