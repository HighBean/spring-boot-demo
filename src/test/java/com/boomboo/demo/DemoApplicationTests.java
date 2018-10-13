package com.boomboo.demo;

import com.boomboo.demo.service.wrapper.AService;
import com.boomboo.demo.service.wrapper.AbstractAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource(name = "aService")
    private AService aService;

    @Test
    public void contextLoads() {
        aService.doA("B");
    }

}
