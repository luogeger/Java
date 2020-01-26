package com.first.designPattern.create.factoryMethod;

public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("send sms");
    }
}
