package com.first.mq.consumer;

import com.first.mq.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author luoxiaoqing
 */
@Component
public class OrderReceiver {

    /**
     *
     * @param order 消息
     * @param headers 消息属性
     * @param channel 手动签收
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable="true"),
            exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
            key = "order.*")
    )
    @RabbitHandler
    public void getMsg(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws Exception{
        System.out.println("-----消息收到-----");
        System.out.println(order.toString());
        //  手动签收, false表示不需要批量签收
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(tag, false);

    }
}
