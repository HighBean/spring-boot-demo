package com.boomboo.demo.java;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.boomboo.demo.vo.PersonVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestJavapecification {
    Logger logger = LoggerFactory.getLogger(TestJavapecification.class);


    @Test
    public void testSplit() {
        String str = "a,a,a,,";
        String[] strStr = str.split(",");
        logger.info("length:{}", strStr.length);
    }

    @Test
    public void testEquals() {
        logger.info("abc" + "aaa".equals(null));
        if (true) {
            logger.info("abc");
            String jsonStr = "";
        }
    }

    @Test
    public void debugLambda() {
        List<String> testList = new ArrayList();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("4");
        testList.add("5");
        testList.add("6");
        testList.stream().filter(i -> (true)).forEach(i -> {
            if ("5".endsWith(i)) {
                testList.add("end");
            }
        });
    }

    @Test
    public void format() {
        PersonVO personVO = new PersonVO();
        ValueFilter filter = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                return value == null ? "" : value;
            }
        };
        logger.info("format data:{}", JSONObject.toJSONString(personVO, filter));
    }


}


