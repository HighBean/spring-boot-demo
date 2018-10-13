package com.boomboo.demo.java.aop;

import org.mockito.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class AroundFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        return "sayHello".equals(method.getName()) ? 1 : 0;
    }
}
