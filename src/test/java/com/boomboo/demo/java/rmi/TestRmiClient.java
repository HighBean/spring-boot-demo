package com.boomboo.demo.java.rmi;

import com.boomboo.demo.DemoApplication;
import com.boomboo.demo.service.rmi.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)
public class TestRmiClient {
    Logger logger = LoggerFactory.getLogger(TestRmiClient.class);

    @Autowired
    ContractService contractClient;

    @Test
    public void testRmi() {
        logger.info("aa:{}", contractClient.sayHello("sun"));
    }

}
