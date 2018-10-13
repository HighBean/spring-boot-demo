package com.boomboo.demo.service.rmi;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("contractService")
public class ContractImpl implements ContractService {

    protected ContractImpl() {
    }

    @Override
    public String sayHello(String name) {
        return "hello!" + name;
    }
}
