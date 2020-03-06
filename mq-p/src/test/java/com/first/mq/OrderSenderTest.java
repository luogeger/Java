package com.first.mq;

import com.first.mq.entity.Order;
import com.first.mq.producer.OrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author luoxiaoqing
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {

    @Autowired
    OrderSender orderSender;

    @Test
    public void main1() throws Exception {
        Order msg = new Order("2019090902",
                "msg test",
                System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderSender.send(msg);
    }
}
