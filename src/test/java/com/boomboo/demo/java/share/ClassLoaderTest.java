package com.boomboo.demo.java.share;


import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 类加载器在类相等判断中的影响
 */

public class ClassLoaderTest {
//    private static Logger logger = LoggerFactory.getLogger(ClassLoaderTest.class);

    @Test
    public void testClassLoader() throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream("ClassLoaderTest.class");
                    if (is == null) {
                        return super.loadClass(fileName);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj2 = myLoader.loadClass("com.boomboo.demo.java.share.ClassLoaderTest").newInstance();
    }

    public void print() {
        System.out.println("print success");
    }

    @Test
    public void testClassLoadPrint() {
//        FileSystemClassLoader fsLoader = new FileSystemClassLoader(Thread.currentThread().getContextClassLoader());
        FileSystemClassLoader fsLoader = new FileSystemClassLoader(null);
        try {
            Class<?> clazz = fsLoader.loadClass("com.boomboo.demo.java.share.ClassLoaderTest");

            Method method = clazz.getMethod("print");
            method.invoke(clazz.newInstance());

//            ClassLoaderTest classLoaderTest = (ClassLoaderTest) clazz.newInstance();
//            classLoaderTest.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}