package com.example.demo_rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RMConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumer(String msg) {
        LOGGER.info(String.format("Received message -> %s", msg));
    }
}
