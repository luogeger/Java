package com.first.mq.entity;

import java.io.Serializable;

/**
 * @author luoxiaoqing
 */
public class Order implements Serializable {

    private String  id;

    private String  data;

    private String  messageId;

    public Order() {
    }

    public Order(String id, String data, String messageId) {
        this.id = id;
        this.data = data;
        this.messageId = messageId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}

