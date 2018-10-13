package com.boomboo.demo.java.share;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 字节码层模拟方法调用
 */

public class TestMethodHandler {
    private static Logger logger = LoggerFactory.getLogger(TestMethodHandler.class);

    static class PrintTest {
        public void print(PrintTest my, String str) {
            System.out.println("printStr:" + str);
        }

        public void print(String str) {
            System.out.println("printStr:" + str);
        }
    }

    @Test
    public void testMethodHandler() throws Throwable {
        Object obj = new PrintTest();
        getPrintlnMethodHandler(obj).invoke("come");
    }

    private static MethodHandle getPrintlnMethodHandler(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        //第一个是方法的返回值 第二个是参数
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "print", methodType).bindTo(reveiver);
    }


    class GrandFather {
        void thinking() {
            System.out.println("grandFa");
        }
    }

    class Father extends GrandFather {
//        void thinking() {
//            System.out.println("father");
//        }
    }

    class Son extends Father {
        void thinking() {
//            GrandFather grandFather = new GrandFather();
//            grandFather.thinking();
            MethodType methodType = MethodType.methodType(void.class);
            try {
                //findSpecial对应的是invokeDynamic指令
                //还有 findStatic、findStatic
                MethodHandle methodHandle = MethodHandles.lookup().findSpecial(GrandFather.class,
                        "thinking", methodType, getClass());
                methodHandle.invoke(this);
            } catch (Throwable e) {
                logger.error("ex", e);
            }
        }
    }

    @Test
    public void testGrandFaThinking() {
        new TestMethodHandler().new Son().thinking();
    }
}
