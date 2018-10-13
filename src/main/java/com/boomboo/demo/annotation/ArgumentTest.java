package com.boomboo.demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArgumentTest {

    enum type {
        A, B;
    }

    String[] params() default "";

    String pattern();

}
