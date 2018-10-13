package com.boomboo.demo.java.hotSwap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalTest {
    Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    @Test
    public void testThreadLocal() {
        Thread t = new Thread() {
            ThreadLocal<String> threadLocal = new ThreadLocal();

            @Override
            public void run() {
                super.run();
                threadLocal.set("aaa");
                logger.info("result:{}", threadLocal.get());
                threadLocal.remove();
            }
        };
        t.run();
    }


}
