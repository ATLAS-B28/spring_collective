package com.example.demo_rabbit.publisher;

import com.example.demo_rabbit.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RMJProducer {

    //exchange
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    //routing key for json
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RMJProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RMJProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMsg(User user) {
        LOGGER.info(String.format("Json Message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }
}
