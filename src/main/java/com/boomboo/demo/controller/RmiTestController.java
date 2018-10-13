package com.boomboo.demo.controller;

import com.boomboo.demo.service.rmi.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo/rmi")
@Slf4j
public class RmiTestController {

    @Resource
    ContractService contractClient;

    @RequestMapping("/test")
    public void testRmi() {
        String jsonStr = "{   \"log\": {     \"loglevel\": \"warning\"   },   \"inbound\": {     \"protocol\": \"vmess\",     \"port\": 8080,     \"settings\": {\"clients\": [         {           \"id\": \"27397758-cc4e-436c-bf3d-b354069af75c\",           \"alterId\": 64,           \"security\":\"aes-128-cfb\"         }       ]     },     \"streamSettings\": {       \"network\": \"ws\"     }   },   \"inboundDetour\": [],   \"outbound\": {     \"protocol\": \"freedom\",    \"settings\": {}   } }";
        log.info("aa:{}", contractClient.sayHello("sun"));
        if (true) {
        }
    }

}
