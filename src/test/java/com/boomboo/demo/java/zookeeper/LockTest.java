package com.boomboo.demo.java.zookeeper;

import com.google.common.base.Strings;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTest {
    private static Logger logger = LoggerFactory.getLogger(LockTest.class);

    private static final String zkHost = "127.0.0.1:2181";

    private static final String parentPath = "/testZk";

    private static final String childrenPath = "/testZk/children";

    private static final CountDownLatch count = new CountDownLatch(1);

    ZooKeeperConn conn;

    ZooKeeper zk;

    public LockTest() {
        conn = new ZooKeeperConn();
        try {
            zk = conn.connect(zkHost);
        } catch (Exception ex) {
            logger.error("zk连接失败", ex);
        }
    }

    public String create(String path, byte[] data) throws
            KeeperException, InterruptedException {
        String createResult = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        logger.info("zookeeper createResult:{}", createResult);
        return createResult;
    }

    private void getRootPath() throws KeeperException, InterruptedException {
        if (zk.exists(parentPath, true) == null) {
            create(parentPath, "parentNode".getBytes());
        }
    }

    private boolean lock() throws KeeperException, InterruptedException {
        String path = null;
        getRootPath();
        //Todo watch node
        while (true) {
            try {
                path = create(childrenPath, "children".getBytes());
            } catch (KeeperException keepEx) {
                logger.info(Thread.currentThread() + "not get");
                Thread.sleep(1000);
            }
            if (!Strings.nullToEmpty(path).trim().isEmpty()) {
                logger.info(Thread.currentThread() + "get lock");
                return true;
            }
        }
    }


    public void unlock() throws KeeperException, InterruptedException {
        zk.delete(childrenPath, -1);
        logger.info("children delete");
    }

    @Test
    public void TestLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    LockTest lockTest = new LockTest();
                    try {
                        lockTest.lock();
                    } catch (Exception e) {
                        logger.error("lock error", e);
                    }
                    try {
                        lockTest.unlock();
                    } catch (Exception ex) {
                        logger.error("unlock error", ex);
                    }
                }
            });
        }
        while (true) {
        }
//        executorService.shutdown();
    }

}
