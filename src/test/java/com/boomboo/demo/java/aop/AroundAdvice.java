package com.boomboo.demo.java.aop;

import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class AroundAdvice implements MethodInterceptor {
    Logger logger = LoggerFactory.getLogger(AroundAdvice.class);

    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.info("Around Start");
        Object rvt = methodProxy.invokeSuper(target, objects);
        logger.info("Around End");
        return rvt;
    }
}
