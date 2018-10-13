package com.boomboo.demo.java.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private io.netty.channel.Channel channel;


    public void run() {
        final EventLoopGroup group = new NioEventLoopGroup(1);

        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(group).channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitializer());
        try {
            channel = bootstrap.bind("localhost", 8088).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    @Test
    public void start() {
        logger.info("nettyService start");
        new NettyServer().run();
    }

    private class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        public void initChannel(final SocketChannel ch) throws Exception {
            final ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast("http-request-decoder", new HttpRequestDecoder());
            pipeline.addLast("aggregator", new HttpObjectAggregator(1));
            pipeline.addLast("http-response-encoder", new HttpResponseEncoder());
            pipeline.addLast("request-handler", new WebSocketServerProtocolHandler("/websocket"));
            pipeline.addLast("handler", new SomeHandler());
        }
    }

    public class SomeHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
            final String x = textWebSocketFrame.text();
            // uncomment to print request
            logger.info("Request received: {}", x);
            final String[] y = x.split(":");
            channelHandlerContext.writeAndFlush(new TextWebSocketFrame(y[0] + ":" + y[1].toUpperCase()));
        }
    }


}
