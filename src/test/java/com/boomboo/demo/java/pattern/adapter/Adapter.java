package com.boomboo.demo.java.pattern.adapter;

public class Adapter extends Adaptee implements Target {

    @Override
    public void print(Object str) {
        new Adaptee().printString(String.valueOf(str));
    }
}
