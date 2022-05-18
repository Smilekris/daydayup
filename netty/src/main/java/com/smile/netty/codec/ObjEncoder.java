package com.smile.netty.codec;

import cn.hutool.core.util.ObjectUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public ObjEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (genericClass.isInstance(o)) {
            byte[] data = ObjectUtil.serialize(o);
            byteBuf.writeInt(data.length);
            byteBuf.writeBytes(data);
        }
    }
}
