package com.boomboo.demo.java.controller;

import com.boomboo.demo.DemoApplication;
import com.boomboo.demo.controller.PersonController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
//@ContextConfiguration(locations = {"classpath:spring/*.xml"})//加载Spring配置文件
public class PersonControllerTest {

    @Resource
    private PersonController personController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testGet() {
        try {
            ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/demo/person/v1/1"));
            MvcResult result = actions.andReturn();
            log.info("response:{}", result.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error("request error", e);
        }
    }

    @Test
    public void testGetRmi() {
        try {
            ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/rmi/test"));
            MvcResult result = actions.andReturn();
            log.info("response:{}", result.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error("request error", e);
        }
    }


}
