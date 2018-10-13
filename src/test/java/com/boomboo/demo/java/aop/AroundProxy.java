package com.boomboo.demo.java.aop;

import org.mockito.cglib.proxy.Callback;
import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.NoOp;

public class AroundProxy {

    public static <T> T getEnhancer(Class tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        //此处并用filter跟callbacks，可以使用多个filter
        enhancer.setCallbacks(new Callback[]{new AroundAdvice(), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new AroundFilter());
        return (T) enhancer.create();
    }
}
