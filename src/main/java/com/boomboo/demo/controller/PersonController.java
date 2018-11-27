package com.boomboo.demo.controller;

import com.boomboo.demo.annotation.ArgumentTest;
import com.boomboo.demo.dto.PersonDTO;
import com.boomboo.demo.service.AsynRequestService;
import com.boomboo.demo.service.PersonService;
import com.boomboo.demo.vo.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dapeng
 */
@Slf4j
@RestController
@RequestMapping("/demo/person")
public class PersonController {

    @Resource(name = "personService1")
    private PersonService personService;

    @Resource
    private AsynRequestService asynRequestService;

    @RequestMapping(value = "/{version:.+}/{id}", method = RequestMethod.GET)
    public PersonVO getPerson(@PathVariable Long id, @PathVariable String version) {
//        Assert.assertEquals("version不正确", version, 2);
        PersonDTO personDTO = personService.getPersonById(id);
        return assemblePersonVO(personDTO);
    }

    @RequestMapping(value = "/{version:.+}/{id}", method = RequestMethod.POST)
    public PersonVO createdPerson(@PathVariable String version, @PathVariable Long id
//            , @RequestBody PersonVO personVO
            , @ArgumentTest(pattern = "ArgumentTest") String argumentTest) {
        //No-op
        log.info("ArgumentTest:{}", argumentTest);
        return new PersonVO();
    }

    @RequestMapping(value = "/test-asyn", method = RequestMethod.GET)
    public void testAsyn() {
        log.info("create time:{}", System.currentTimeMillis());
        asynRequestService.testAsyn();
        log.info("leave time:{}", System.currentTimeMillis());
    }


    private PersonVO assemblePersonVO(PersonDTO personDTO) {
        return new PersonVO(personDTO.getName(), personDTO.getAge());
    }

}
