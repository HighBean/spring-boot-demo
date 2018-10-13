package com.boomboo.demo.java.share;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * findClass()方法首先根据类的全名在硬盘上查找类的字节代码文件（.class 文件），然后读取该文件内容，最后通过 defineClass()方法来把这些字节代码转换成 java.lang.Class类的实例。
 */

public class FileSystemClassLoader extends ClassLoader {
    Logger logger = LoggerFactory.getLogger(FileSystemClassLoader.class);

    private String rootDir = "/Users/**/**/target/test-classes/";

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        System.out.println("path:" + path);
        try {
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                baos.write(b);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            logger.error("fileSystemClassLoader fileNotFound Error", e);
        } catch (IOException e) {
            logger.error("fileSystemClassLoader IOException Error", e);
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar + className.replaceAll("\\.", "\\/") + ".class";
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public FileSystemClassLoader(ClassLoader parent) {
        super(parent);
    }
}
