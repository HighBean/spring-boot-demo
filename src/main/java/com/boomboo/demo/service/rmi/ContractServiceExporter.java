package com.boomboo.demo.service.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
//
//@Component
//public class ContractServiceExporter {
//
//    @Bean
//    public RmiServiceExporter rmiExporter(ContractService contractService) {
//        RmiServiceExporter exporter = new RmiServiceExporter();
//        exporter.setService(contractService);
//        exporter.setServiceName("contractService");
//        exporter.setServiceInterface(ContractService.class);
////        exporter.setRegistryHost("127.0.0.1");
//        exporter.setRegistryPort(8181);
//        return exporter;
//    }
//
//
//}
