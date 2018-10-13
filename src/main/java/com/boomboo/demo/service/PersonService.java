package com.boomboo.demo.service;

import com.boomboo.demo.dto.PersonDTO;

public interface PersonService {
    Integer getAgeByName(String name);

    PersonDTO getPersonById(Long id);
}
