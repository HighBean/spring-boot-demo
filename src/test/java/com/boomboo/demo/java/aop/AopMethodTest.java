package com.boomboo.demo.java.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopMethodTest {
    Logger logger = LoggerFactory.getLogger(AopMethodTest.class);

    public String sayHello(String who, String word) {
        logger.info("who:{},word:{}", who, word);
        return who + word;
    }

    public String sayNotIntercept(String who, String word) {
        logger.info("who:{},word:{}", who, word);
        return who + word;
    }

}
