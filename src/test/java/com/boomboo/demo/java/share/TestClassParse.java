package com.boomboo.demo.java.share;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClassParse {
    Logger logger = LoggerFactory.getLogger(TestClassParse.class);

    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init method");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    interface IndexInterface {
        int a = 0;
    }

    interface IndexInterface1 {
        int a = 1;
    }

    interface IndexExtend extends IndexInterface {
        int a = 1;
    }

    static class Parent implements IndexExtend {
        public static int a = 3;
    }

    static class Sub extends Parent implements IndexInterface1 {
        public static int a = 4;
    }

    @Test
    public void testStaticA() {
        logger.info("aa:{}", Sub.a);

        Runnable threadRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                TestClassParse parse = new TestClassParse();
                logger.info(Thread.currentThread() + "end");
            }
        };

        Thread testThread1 = new Thread(threadRunnable);
        Thread testThread2 = new Thread(threadRunnable);

        testThread1.run();
        testThread2.run();
    }


}
