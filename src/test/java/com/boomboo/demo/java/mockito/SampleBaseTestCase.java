package com.boomboo.demo.java.mockito;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class SampleBaseTestCase {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
