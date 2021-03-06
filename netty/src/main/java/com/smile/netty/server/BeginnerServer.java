package com.smile.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BeginnerServer {
    private static int port = 8000;
    public static void main(String[] args) throws Exception {
      new BeginnerServer().run();
    }

    public  void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //对象传输处理
                            ch.pipeline().addLast(new ObjDecoder(SendMessage.class));
                            ch.pipeline().addLast(new ObjEncoder(FeedBackMessage.class));
                            // 在管道中添加我们自己的接收数据实现方法
                            ch.pipeline().addLast(new TimeServerHandler());
                        }
                    });

            // bind and start to accept incoming connections
            ChannelFuture f = b.bind(port).sync();
            // shut down your server
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
