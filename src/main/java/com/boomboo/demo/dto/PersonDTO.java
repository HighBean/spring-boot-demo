package com.boomboo.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class PersonDTO implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    public PersonDTO(Long id) {
        this.id = id;
    }

    public PersonDTO() {
    }
}
