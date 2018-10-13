package com.boomboo.demo.java.nio;

//import com.mindhaq.apiversions.util.FastJsonUtil;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler extends SimpleChannelInboundHandler<Object> {
    private static Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private final WebSocketClientHandshaker handshaker;

    private ChannelPromise channelPromise;

    public ChannelFuture handshakeFuture() {
        return channelPromise;
    }

    public NettyClientHandler(final WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext handlerContext, Object msg) throws Exception {
        final Channel channel = handlerContext.channel();
        if (!handshaker.isHandshakeComplete()) {
            handshaker.finishHandshake(channel, (FullHttpResponse) msg);
            channelPromise.setSuccess();
            return;
        }
        if (msg instanceof FullHttpResponse) {
            final FullHttpResponse response = (FullHttpResponse) msg;
//            logger.error("httpResponse unexpected:{}", FastJsonUtil.toJsonString(response));
            throw new Exception("httpResponse unexpected:" + response);
        }
        final WebSocketFrame socketFrame = (WebSocketFrame) msg;
        if (socketFrame instanceof TextWebSocketFrame) {
            final TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;
            logger.info("textFrame:{}", textWebSocketFrame);
        } else if (socketFrame instanceof PongWebSocketFrame) {
            logger.info("pongFrame:{}", socketFrame);
        } else if (socketFrame instanceof CloseWebSocketFrame) {
            logger.info("closeFrame:{}", socketFrame);
            channel.close();
        } else if (socketFrame instanceof BinaryWebSocketFrame) {
            logger.info("binaryFrame:{}", socketFrame);
        }
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        channelPromise = ctx.newPromise();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        logger.error("WebSocket Client disconnected!");
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        cause.printStackTrace();

        if (!channelPromise.isDone()) {
            channelPromise.setFailure(cause);
        }

        ctx.close();
    }

}
