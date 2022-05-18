package com.smile.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class BeginnerClient {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8000;

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    //对象传输处理
                    ch.pipeline().addLast(new ObjDecoder(FeedBackMessage.class));
                    ch.pipeline().addLast(new ObjEncoder(SendMessage.class));
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            // Start the client
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().writeAndFlush(SendMessage.build("你好，使用protobuf通信格式的服务端，我是客户端。", f.channel().id().toString()));
            // Wait until the connection is closed
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
