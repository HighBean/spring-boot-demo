package com.boomboo.demo.java.hotSwap;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

public class ExecutorProxy implements Executor {
    private String version;
    private StandardExecutorClassLoader classLoader;

    public ExecutorProxy(String version) {
        this.version = version;
        classLoader = new StandardExecutorClassLoader(version);
    }

    @Override
    public void execute(Runnable name) {
        try {
            // Load ExecutorProxy class
            Class<?> executorClazz = classLoader.loadClass("Executor" + version.toUpperCase());

            Object executorInstance = executorClazz.newInstance();
            Method method = executorClazz.getMethod("execute", String.class);

            method.invoke(executorInstance, name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
