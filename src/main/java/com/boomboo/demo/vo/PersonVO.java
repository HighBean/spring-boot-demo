package com.boomboo.demo.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class PersonVO implements Serializable {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    public PersonVO() {
    }

    public PersonVO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
