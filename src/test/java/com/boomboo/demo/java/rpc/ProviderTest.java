package com.boomboo.demo.java.rpc;

import com.boomboo.demo.rpc.RpcFramework;
import com.boomboo.demo.service.PersonService;
import com.boomboo.demo.service.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ProviderTest {

    @Test
    public void exportService() {
        PersonService personService = new PersonServiceImpl();
        try {
            new RpcFramework().export(personService, 1111);
        } catch (Exception e) {
            log.error("export service error", e);
        }

    }

}
