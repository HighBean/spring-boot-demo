package com.boomboo.demo.java.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnnotationMockitoLearn {

    @Mock
    private ArrayList<String> list;

    @Spy
    private List<String> spyList = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        MockitoAnnotations.initMocks(this);

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());

        spyList.add("111");
        assertEquals("111", spyList.get(2));

        list.add("1211");

        verify(list).add("1211");

        when(list.get(0)).thenReturn("1211");

        assertEquals("1211", list.get(0));

    }


}
