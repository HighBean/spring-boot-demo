package com.boomboo.demo.java.pool;


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @target 做一个基础的线程池
 */
public class SmallThreadPool {
    //任务队列
//    private final BlockingQueue<Runnable> workQueue;

    //线程池的状态锁（Run、ShunDown、Stop、Terminated）
    private final ReentrantLock mainQueueLock = new ReentrantLock();

    //工作集
//    private final Set<Worker> workers = new HashSet<>();

    //线程存活时间
    private volatile long keepAliveTime;

    //核心线程设置超时?
    private volatile boolean allowCoreThreadTimeOut;

    //核心池大小 ：running size
    private volatile int corePoolSize;

    //最大线程数
    private volatile int maxPoolSize;

    //线程池当前运行线程数
    private volatile int poolSize;

    //任务拒绝时的策略
    private volatile RejectedExecutionHandler handler;

    //线程工厂 创建线程
    private volatile ThreadFactory factory;

    //线程池中的最大线程数
    private int largestPoolSize;

    //完成的任务数
    private long completedTaskCount;

    private final AtomicInteger aInt = new AtomicInteger();

    private final ReentrantLock mainLock = new ReentrantLock();

    private final Condition termination = mainLock.newCondition();


    public void execute(Runnable command) {


    }


}
