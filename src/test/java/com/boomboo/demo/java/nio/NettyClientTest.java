package com.boomboo.demo.java.nio;//package nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NettyClientTest {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientTest.class);

    @Test
    public void testNettyClient() {
        final String uri = "ws://localhost:8088/websocket";
        NettyClient nettyClient = new NettyClient(uri);
        try {
            nettyClient.open();
            final String fatty = IntStream.range(0, 1024).mapToObj(String::valueOf).collect(Collectors.joining());
            nettyClient.<String>writeAndFlush(UUID.randomUUID().toString() + ":" + fatty);
            nettyClient.close();
        } catch (InterruptedException e) {
            logger.error("nettyClient error:{}", e);
        }
    }
}
