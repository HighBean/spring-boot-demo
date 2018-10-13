package com.boomboo.demo.java.SelfAnnotation;

import org.junit.Test;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.lang.reflect.Method;

/**
 * Created by dapeng on 2017/6/21.
 */
@ControllerAdvice
public class AnnotationHandler {

    @Test
    public void handAnnotation() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AnnotationTestVo myTestClass = (AnnotationTestVo) Class.forName("com.boomboo.demo.java.SelfAnnotation.AnnotationTestVo").newInstance();
        Class myClass = myTestClass.getClass();
        if (myClass.isAnnotationPresent(Insert.class)) {
            Insert annoInsert = (Insert) myClass.getAnnotation(Insert.class);
            System.out.println("Annotation value is" + annoInsert.value());
        }

        Method[] methods = myTestClass.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Insert.class)) {
                Insert annotationInsert = (Insert) method.getAnnotation(Insert.class);
                System.out.println("annotationInsert" + annotationInsert == null ? "" : annotationInsert.value());
            }
        }
    }
}
