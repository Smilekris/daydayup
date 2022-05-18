package com.smile.netty.codec;

import java.io.Serializable;

public class FeedBackMessage<T> implements Serializable {

    private String code;
    private String message;
    private String channelId;
    private T data;

    public FeedBackMessage() {

    }

    public FeedBackMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static <T> FeedBackMessage SuccessMessage() {
        return new FeedBackMessage("200", "请求成功");
    }

    public static <T> FeedBackMessage SuccessMessage(T data) {
        FeedBackMessage<T> feedBackMessage = SuccessMessage();
        feedBackMessage.setData(data);
        return feedBackMessage;
    }

    public static <T> FeedBackMessage SuccessMessage(T data, String channelId) {
        FeedBackMessage<T> feedBackMessage = SuccessMessage(data);
        feedBackMessage.setChannelId(channelId);
        return feedBackMessage;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
