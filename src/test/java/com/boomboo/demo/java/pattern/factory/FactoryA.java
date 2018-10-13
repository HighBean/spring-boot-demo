package com.boomboo.demo.java.pattern.factory;

public class FactoryA extends Factory {
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}
