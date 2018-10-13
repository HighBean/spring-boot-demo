package com.boomboo.demo.java.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 流程
 */
@Slf4j
public abstract class Process {
    public final void process() {
        one();
        two();
        three();
    }

    void one() {
        log.info("do one");
    }

    void two() {
        log.info("do two");
    }

    void three() {
        log.info("do three");
    }
}
