package com.boomboo.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SyncTest {

    @Test
    public void doSync() {
        ThreadTest threadTest = new ThreadTest(new Task());
        threadTest.start();

        ThreadTest threadTest2 = new ThreadTest(new Task());
        threadTest2.start();
        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ThreadTest extends Thread {

        private Task task;

        public ThreadTest(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            task.run();
        }
    }


    class ThreadTestB extends Thread {

        private TaskC task;

        public ThreadTestB(TaskC task) {
            this.task = task;
        }

        @Override
        public void run() {
            task.run();
        }
    }


    class Task {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("thread:{},i:{}", Thread.currentThread().getName(), i);
            }
            synchronized (SyncTest.class) {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("Bthread:{},i:{}", Thread.currentThread().getName(), i);
                }
            }

        }

    }

    class TaskC {
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("C1thread:{},i:{}", Thread.currentThread().getName(), i);
            }
            synchronized (SyncTest.class) {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("C2thread:{},i:{}", Thread.currentThread().getName(), i);
                }
            }

        }

    }

}
