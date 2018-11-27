package com.boomboo.demo.service.wrapper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("aService1")
public class AServiceWrapper implements AService, ApplicationContextAware {

    private ApplicationContext context;

    private List<AbstractAService> aServiceImpls;

    private AbstractAService service;

    @Override
    public void doA(String str) {
        if (aServiceImpls == null) {
            init();
        }
        for (AbstractAService wrapper : aServiceImpls) {
            if (wrapper.support(str)) {
                this.service = wrapper;
                break;
            }
        }
        this.service.executeA();
    }

    public void init() {
        synchronized (AbstractAService.class) {
            if (aServiceImpls == null) {
                Map<String, AbstractAService> matchingBeans =
                        BeanFactoryUtils.beansOfTypeIncludingAncestors(context, AbstractAService.class, true, false);
                aServiceImpls = new ArrayList<>(matchingBeans.values());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
