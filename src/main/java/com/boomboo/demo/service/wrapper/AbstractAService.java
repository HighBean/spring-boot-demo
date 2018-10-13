package com.boomboo.demo.service.wrapper;


public abstract class AbstractAService extends AServiceWrapper {

    public AbstractAService() {
    }

    public abstract void executeA();

    public abstract boolean support(String str);

}
