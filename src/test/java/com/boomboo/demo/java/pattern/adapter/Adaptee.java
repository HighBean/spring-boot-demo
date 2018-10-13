package com.boomboo.demo.java.pattern.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Adaptee {

    public void printString(String str) {
        log.info("print:{}", str);
    }
}
