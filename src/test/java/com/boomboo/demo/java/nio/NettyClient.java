package com.boomboo.demo.java.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class NettyClient {
    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private final URI uri;

    private Channel channel;

    private final int maxPayload = 1280000;

    private static final EventLoopGroup group = new NioEventLoopGroup();

    public NettyClient(final String uri) {
        this.uri = URI.create(uri);
    }

    public void open() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        String protocol = uri.getScheme();
//        if (!"was".equals(protocol)) {
//            logger.error("illegal protocol");
//            throw new IllegalArgumentException("illegal protocol");
//        }
        // Connect with V13 (RFC 6455 aka HyBi-17). You can change it to V08 or V00.
        // If you change it to V00, ping is not supported and remember to change
        // HttpResponseDecoder to WebSocketHttpResponseDecoder in the pipeline.
        final NettyClientHandler clientHandler = new NettyClientHandler(
                WebSocketClientHandshakerFactory.newHandshaker(
                        uri, WebSocketVersion.V13, null, false,
                        HttpHeaders.EMPTY_HEADERS, maxPayload));
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpClientCodec());
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                        pipeline.addLast("ws-handler", clientHandler);
                    }
                });
        channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
        clientHandler.handshakeFuture().sync();
    }

    public void close() throws InterruptedException {
        logger.info("close nettyClient");
        channel.writeAndFlush(new CloseWebSocketFrame());
        channel.closeFuture().sync();
    }

    public void writeAndFlush(final String text) {
        channel.writeAndFlush(new TextWebSocketFrame(text));
    }


}
