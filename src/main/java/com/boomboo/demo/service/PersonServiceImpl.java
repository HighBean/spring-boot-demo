package com.boomboo.demo.service;

import com.boomboo.demo.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author dapeng
 * @Desc 个人Service
 */
@Slf4j
@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Override
    public Integer getAgeByName(String name) {
        return 12;
    }

    @Override
    public PersonDTO getPersonById(Long id) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            log.error("thread sleep 2s error", e);
//        }
        return new PersonDTO(12L);
    }

}
