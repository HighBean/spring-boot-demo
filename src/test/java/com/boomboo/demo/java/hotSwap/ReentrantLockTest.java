package com.boomboo.demo.java.hotSwap;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    public void method1() {
        synchronized (ReentrantLockTest.class) {
            System.out.println("方法1获得ReentrantTest的内置锁运行了");
            method2();
        }
    }

    public void methodReentrant() {
        lock.lock();
        try {
            System.out.println("方法1获得ReentrantTest的内置锁运行了");
            methodReentrant2();
        } finally {
            System.out.println("方法1释放锁");
            lock.unlock();
        }
    }

    public void method2() {
        synchronized (ReentrantLockTest.class) {
            System.out.println("方法1里面调用的方法2重入内置锁,也正常运行了");
        }
    }

    public void methodReentrant2() {
        lock.lock();
        try {
            System.out.println("方法1里面调用的方法2重入ReentrantLock锁,也正常运行了");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new ReentrantLockTest().method1();
    }


}
