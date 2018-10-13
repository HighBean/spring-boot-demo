package com.boomboo.demo.java.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class OffHeapMemoryTest {
    Logger logger = LoggerFactory.getLogger(OffHeapMemoryTest.class);

    /**
     * 该类主要是在通信过程中作为缓冲池，例如netty、mina（NIO）
     * directMemore default:64M
     * <p>
     * 对于频繁使用的IO内存，存在时间短，推荐使用堆外内存从
     * 不能使用大量堆外内存，需要控制gc，可以通过反射进行System.gc，有可能造成OOM
     * 非线程安全，对write操作 syn一下
     * <p>
     * Direct Buffer的JAVA对象是归GC管理的，只要GC回收了它的JAVA对象，操作系统才会释放Direct Buffer所申请的空间
     */
    @Test
    public void testDirectByteBuffer() {
//        ByteBuffer heapBuffer = ByteBuffer.allocate(1024);
//        heapBuffer.put("abc".getBytes());
//        heapBuffer.flip();
//        logger.info("heapBuffer:{}", heapBuffer.array());
//        logger.info("abc:{}", "abc".getBytes());
//        //长度扩展
//        heapBuffer.compact();
//        heapBuffer.put("bcd3fasefg43fadfaee32fased".getBytes());
//        heapBuffer.flip();
//        heapBuffer.compact();
//        logger.info("heapBuffer:{}", heapBuffer.array());
//        logger.info("abc:{}", "bcd".getBytes());

        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);


        directBuffer.put("abc".getBytes());
        directBuffer.flip();
        logger.info("directBuffer:{}", directBuffer.get());
        logger.info("abc:{}", "abc".getBytes());
        //长度扩展
        directBuffer.compact();
        directBuffer.put("bcd3fasefg43fadfaee32fased".getBytes());
        directBuffer.flip();
        directBuffer.compact();

        //因为是直接内存，无法转换成堆内存
        if (directBuffer.hasArray()) {
            logger.info("directBuffer:{}", directBuffer.array());
        } else {
            logger.info("directBuffer:{}", directBuffer.get(1));
        }
//        PooledByteBufAllocator.
        logger.info("abc:{}", "bcd".getBytes());
    }


}
