package com.boomboo.demo.java.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyA extends StrategyInterface {

    @Override
    public void show() {
        log.info("show A");
    }
}
