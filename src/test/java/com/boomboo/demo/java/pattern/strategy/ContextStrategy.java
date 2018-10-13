package com.boomboo.demo.java.pattern.strategy;

/**
 * 运行上下文
 */
public class ContextStrategy {

    private StrategyInterface strategyInterface;

    public StrategyInterface handleStrategy(String strategy) {
        switch (strategy) {
            case "A":
                strategyInterface = new StrategyA();
                break;
            case "B":
                strategyInterface = new StrategyB();
                break;
            default:
                throw new RuntimeException("暂无匹配");
        }
        return strategyInterface;

    }

    public void show() {
        strategyInterface.show();
    }

}
