package com.smile.netty.codec;

import java.io.Serializable;

public class SendMessage implements Serializable {
    private String message;
    private String channelId;

    public SendMessage() {}

    public SendMessage(String message, String channelId) {
        this.message=message;
        this.channelId=channelId;
    }

    public static SendMessage build(String message, String channelId) {
        return new SendMessage(message, channelId);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
