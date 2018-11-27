package com.boomboo.demo.java;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Notify {

//    class Broker {
//        synchronized void waitCall() {
//            while (!Thread.interrupted()) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                log.info("current:{}", Thread.currentThread());
//            }
//        }
//
//        synchronized void prod() {
//            notify();
//        }
//
//        synchronized void prodAll() {
//            notifyAll();
//        }
//    }
//
//
//    class TaskA implements Runnable {
//        @Getter
//        @Setter
//        public Broker broker = new Broker();
//
//        @Override
//        public void run() {
//            broker.waitCall();
//        }
//    }
//
//    class TaskB implements Runnable {
//        Broker broker = new Broker();
//
//        @Override
//        public void run() {
//            broker.waitCall();
//        }
//    }

    @Test
    public void test() {
        try {
            wait();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        ExecutorService service = Executors.newFixedThreadPool(6);
//        for (int i = 0; i < 5; i++) {
//            service.execute(new TaskA());
//        }
//        service.execute(new TaskB());
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            boolean prod = true;
//
//            @Override
//            public void run() {
//                if (prod) {
//                    log.info("notify:{}", Thread.currentThread());
//                    broker
//                }
//
//
//            }
//        });

    }


}
