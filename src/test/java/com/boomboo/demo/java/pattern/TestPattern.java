package com.boomboo.demo.java.pattern;

import com.boomboo.demo.java.pattern.factory.FactoryA;
import org.junit.Test;
import com.boomboo.demo.java.pattern.adapter.Adapter;
import com.boomboo.demo.java.pattern.adapter.Target;
import com.boomboo.demo.java.pattern.strategy.ContextStrategy;
import com.boomboo.demo.java.pattern.strategy.StrategyInterface;
import com.boomboo.demo.java.pattern.template.ConcreteProcess;

public class TestPattern {

    @Test
    public void testAdapter() {
        Target target = new Adapter();
        target.print("a");
    }

    @Test
    public void testStrategy() {
        StrategyInterface strategy = new ContextStrategy().handleStrategy("A");
        strategy.show();
    }

    @Test
    public void testTemplate() {
        ConcreteProcess process = new ConcreteProcess();
        process.process();
    }

    @Test
    public void testFactory() {
        FactoryA a = new FactoryA();
        a.Manufacture().show();
    }
}
