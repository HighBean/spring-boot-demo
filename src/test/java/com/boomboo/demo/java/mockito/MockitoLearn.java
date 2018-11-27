package com.boomboo.demo.java.mockito;

import com.boomboo.demo.service.wrapper.AbstractAService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
public class MockitoLearn {

    @Test
    public void standard() {
        //mock creation
        List mockedList = mock(List.class);
        //using mock object
        mockedList.add("one");
        mockedList.clear();
        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testList() {
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
//        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void testListElement() {
        LinkedList<String> mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        //argument matchers can also be written as Java 8 Lambdas
//        verify(mockedList).add(argThat( someString -> someString. > 5));

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");

    }

    @Test
    public void testThrowEx() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();

        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

//        //create inOrder object passing any mocks that need to be verified in order
//        InOrder inOrder = inOrder(firstMock, secondMock);
//
//        //following will make sure that firstMock was called before secondMock
//        inOrder.verify(firstMock).add("was called first");
//        inOrder.verify(secondMock).add("was called second");
//
//        // Oh, and A + B can be mixed together at will

    }

    /**
     * 判断某些mock没有被调用
     */
    @Test
    public void testInteractions() {
        LinkedList mockOne = mock(LinkedList.class);
        LinkedList mockTwo = mock(LinkedList.class);
        LinkedList mockThree = mock(LinkedList.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testConsecutiveCall() {
        List mock = mock(ArrayList.class);
        when(mock.add(anyString()))
//                .thenThrow(new RuntimeException())
                .thenReturn(false);

        //First call: throws runtime exception:
//        mock.add("some arg");

        //Second call: prints "foo"
        log.info("print:{}", mock.add("some arg"));

        /**
         * 调用内嵌
         */
        when(mock.add(anyString())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                log.info("answerArgs:{},mock:{}", args, mock);
                return true;
            }
        });

        //the following prints "called with arguments: foo"
        log.info("answer:{}", mock.add("foo"));
    }

    /**
     * 参数捕获器
     */
    @Test
    public void testParameterCaptor() {
        List<String> strList = mock(ArrayList.class);
        strList.addAll(Lists.newArrayList("someElement", "someElements"));
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(strList).addAll(argumentCaptor.capture());
        List<String> capturedArgument = argumentCaptor.<List<String>>getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }


    /**
     * 使用spy
     */
    @Test
    public void useSpy() {
        List list = new LinkedList();
        List spyList = spy(list);
//        when(spyList.size()).thenReturn(100);
        spyList.add("one");
        spyList.add("two");
//        Assert.assertEquals(100, spyList.size());
        verify(spyList, times(1)).add("one");
        verify(spyList, times(1)).add("two");
        log.info("list size:{}", spyList.size());

        //Important
        List anotherSpy = spy(list);
        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
//        when(anotherSpy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(anotherSpy).get(0);
    }

    /**
     * 重置mock
     *
     * @warn Don't harm yourself. reset() in the middle of the test method is a code smell (you're probably testing too much).
     */
    @Test
    public void testResetMock() {
        List mockList = mock(ArrayList.class);
        when(mockList.add(anyString())).thenReturn(true);
        log.info("result:{}", mockList.add("1"));
        reset(mockList);
        log.info("result:{}", mockList.add("1"));

        List spyList = spy(new ArrayList());
        when(spyList.add(anyString())).thenReturn(true);
        spyList.add("1");
        log.info("result:{}", spyList.size());
        reset(spyList);
        log.info("result:{}", spyList.size());
    }

    @Test
    public void oneLineStub() {
        List mockList = mock(ArrayList.class);
        when(mock(ArrayList.class).add("")).thenThrow(new Class[]{ArrayList.class}).getMock();


    }


    @Test
    public void BehaviorDrivenDevelopment() {
        AbstractAService mockAbastractAService = spy(AbstractAService.class);




    }


}
