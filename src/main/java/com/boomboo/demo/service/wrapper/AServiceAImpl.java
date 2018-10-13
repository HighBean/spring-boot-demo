package com.boomboo.demo.service.wrapper;

import org.springframework.stereotype.Component;

@Component
public class AServiceAImpl extends AbstractAService {

    @Override
    public void executeA() {
        System.out.println("AImpl");
    }

    @Override
    public boolean support(String str) {
        return "A".equals(str);
    }
}
