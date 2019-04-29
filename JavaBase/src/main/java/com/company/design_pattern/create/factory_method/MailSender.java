package com.company.design_pattern.create.factory_method;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("send mail");
    }
}
