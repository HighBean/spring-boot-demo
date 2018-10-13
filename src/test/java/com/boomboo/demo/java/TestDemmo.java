package com.boomboo.demo.java;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestDemmo {
    Logger logger = LoggerFactory.getLogger(TestDemmo.class);


    @Test
    public void testEquals() {
        logger.info("abc" + "aaa".equals(null));
        if (true) {
            logger.info("abc");
            String jsonStr = "";
        }
    }

    @Test
    public void debugLambda() {
        List<String> testList = new ArrayList();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        testList.add("5");
        testList.add("6");
        testList.stream().filter(i -> (true)).forEach(i -> {
            if ("5".endsWith(i)) {
                testList.add("end");
            }
        });


    }


}


