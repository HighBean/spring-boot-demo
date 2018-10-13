package com.boomboo.demo.java.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperTest {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);

    private static ZooKeeper zk;

    private static ZooKeeperConn conn;

    public static String create(String path, byte[] data) throws
            KeeperException, InterruptedException {
        String createResult = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        logger.info("zookeeper createResult:{}", createResult);
        return createResult;
    }

    public static Stat existNode(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    public static String getData(String path, Stat stat) throws KeeperException, InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        return new String(zk.getData(path, initWatch(path, count), stat));
    }

    public static Stat setData(String path, String data, int version) throws KeeperException, InterruptedException {
        return zk.setData(path, data.getBytes(), version);
    }

    public static List<String> getChildren(String path) throws KeeperException, InterruptedException {
        CountDownLatch count = new CountDownLatch(1);
        return zk.getChildren(path, initWatch(path, count));
    }

    public static void deleteNode(String path, int version) throws KeeperException, InterruptedException {
        zk.delete(path, version);
    }

    @Test
    public void testZooNode() {
        String path = "/testNode";
        byte[] data = "testNode".getBytes();//init data
        try {
            conn = new ZooKeeperConn();
            zk = conn.connect("127.0.0.1:2181");
            String childrenPath = "/testNode/children1";
            String children2Path = "/testNode/children2";
            //            create(path, data);
            create(childrenPath, "children1".getBytes());
//            c、reate(children2Path, "children2".getBytes());
//            List<String> childrens = getChildren(path);
//            logger.info("childrens:{}", childrens);
            Stat nodeStat = existNode(path);
//            logger.info("nodeData:{}", nodeStat == null ? "node is null" : nodeStat.getVersion());
//            if (nodeStat != null) {
//                logger.info("node data:{}", getData(path, nodeStat));
//                setData(path, "changed", nodeStat.getVersion());
//                logger.info("node data changed:{}", getData(path, nodeStat));
//            }

            deleteNode(childrenPath, existNode(childrenPath).getVersion());
            logger.info("delete result:{}", getChildren(path));
        } catch (Exception e) {
            logger.error("aa:{}", e.getMessage()); //Catch error message
        } finally {
            try {
                conn.close();
            } catch (InterruptedException e) {
                logger.error("zk close 遇到ex", e);
            }
        }
    }

    private static Watcher initWatch(String path, CountDownLatch count) {
        return new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.None) {
                    switch (event.getState()) {
                        case Expired:
                            count.countDown();
                            break;
                    }
                } else {
                    try {
                        byte[] data = zk.getData(path, false, null);
                        logger.info("data:{}", new String(data));
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
        };


    }


}
