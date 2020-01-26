package com.first.designPattern.create.factoryMethod;

public class SendFactory {

    public void send(String type) {
        if ("mail".equals(type)) {
            new MailSender().send();
        } else if ("sms".equals(type)) {
            new SmsSender().send();
        } else {
            System.out.println("请输入正确的发送内容...");
        }
    }
}
