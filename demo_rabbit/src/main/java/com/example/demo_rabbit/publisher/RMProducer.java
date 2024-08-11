package com.example.demo_rabbit.publisher;

import org.apache.commons.logging.LogFactoryService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RMProducer {
    //register as service

    //inject the exchange and routing key for the producer to use
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RMProducer.class);

    //now we use rabbit template to send a message automatically
    private final RabbitTemplate rabbitTemplate;

    public RMProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(String msg) {
        LOGGER.info(String.format("Message sent -> %s", msg));

        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
    }
}
