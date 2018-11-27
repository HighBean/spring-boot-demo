package com.boomboo.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ThreadStop {

    @Test
    public void threadStop() {
        Thread testA = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("thred id:{}", Thread.currentThread().getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("sleep:{}", e);
                }
            }
        });

        testA.start();
        try {
            Thread.sleep(1000);
            testA.interrupt();
            log.info("Thead stop result:{}", testA.isInterrupted());
        } catch (InterruptedException e) {
            log.info("sleep error", e);
        }


    }
}
