package com.boomboo.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsynRequestService {

    @Async
    public void testAsyn() {
        long time = System.currentTimeMillis();
        log.info("thread id:{}", Thread.currentThread().getId());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("thread sleep error", e);
        }
        log.info("asyn total time:" + (System.currentTimeMillis() - time));
    }


}
