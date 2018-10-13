package com.boomboo.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ImportResource;

@Slf4j
//@EnableEurekaServer
@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class).web(true).run(args);
//        SpringApplication.run(DemoApplication.class, args);
        log.info("demoApplication-start");
    }
}
