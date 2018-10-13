package com.boomboo.demo.service.wrapper;

import org.springframework.stereotype.Component;

@Component
public class AServiceBImpl extends AbstractAService {

    @Override
    public void executeA() {
        System.out.println("BServiceImpl");
    }

    @Override
    public boolean support(String str) {
        return "B".equals(str);
    }
}
