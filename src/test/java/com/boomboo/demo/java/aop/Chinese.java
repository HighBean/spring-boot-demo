package com.boomboo.demo.java.aop;

import java.io.Serializable;

public class Chinese implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public String getName(String name) {
        this.name = name;
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
