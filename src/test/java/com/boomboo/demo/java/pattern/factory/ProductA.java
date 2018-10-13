package com.boomboo.demo.java.pattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductA extends Product {
    @Override
    public void show() {
        log.info("product A");
    }
}
