package com.first.mq.producer;

import com.first.mq.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luoxiaoqing
 */
@Component
public class OrderSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发送mq
     * @param order
     * @throws Exception
     */
    public void send(Order order) throws Exception {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());

        /**
         * Convert a Java object to an Amqp {@link Message} and send it to a specific exchange
         * with a specific routing key.
         *
         * @param exchange the name of the exchange
         * @param routingKey the routing key
         * @param message a message to send
         * @param correlationData data to correlate publisher confirms.
         */
        rabbitTemplate.convertAndSend("order-exchange", "order.abc", order, correlationData);
    }


}
