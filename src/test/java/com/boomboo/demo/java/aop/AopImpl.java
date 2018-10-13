package com.boomboo.demo.java.aop;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopImpl {
    Logger logger = LoggerFactory.getLogger(AopImpl.class);

    public void print() {
        logger.info("aopImpl running");
    }

    @Test
    public void testAbc() {
        AopMethodTest aopMethodTest = AroundProxy.getEnhancer(AopMethodTest.class);
        logger.info("name:" + aopMethodTest.sayHello("Li", "abc"));
        logger.info("name:" + aopMethodTest.sayNotIntercept("Li", "abc"));
    }


}
