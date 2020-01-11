package com.company.designPattern.create.factoryMethod;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("send mail");
    }
}
