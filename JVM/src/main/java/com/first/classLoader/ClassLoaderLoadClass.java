package com.first.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 重写loadClass方法，打破双亲委派，实现热加载
 */
public class ClassLoaderLoadClass extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        File f = new File("C:/PC/workspace/clone/Java/JVM/target/classes/" + name.replace(".", "/").concat(".class"));

        if (!f.exists()) {
            return super.loadClass(name);
        }

        try {
            InputStream is = new FileInputStream(f);
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.loadClass(name);
    }
}
