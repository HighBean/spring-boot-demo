package com.boomboo.demo.java.rpc;

import com.boomboo.demo.rpc.RpcFramework;
import com.boomboo.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ConsumerTest {

    @Test
    public void consumeService() {
        PersonService personService = new RpcFramework().refer(PersonService.class, "127.0.0.1", 1111);
        Integer age = personService.getAgeByName("1");
        log.info("age:{}", age);
    }

}
