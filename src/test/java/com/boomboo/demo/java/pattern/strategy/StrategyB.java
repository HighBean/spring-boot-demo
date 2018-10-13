package com.boomboo.demo.java.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 策略B
 */
@Slf4j
public class StrategyB extends StrategyInterface {

    @Override
    public void show() {
        log.info("show B");
    }
}
