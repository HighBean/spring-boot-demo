package com.boomboo.demo.service.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-demo.xml"});
        context.start();
        log.info("Provider start~");
        System.in.read(); // press any key to exit
    }

}
