package com.example.demo_rabbit.consumer;

import com.example.demo_rabbit.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RMJConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RMJConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMsg(User user) {

        LOGGER.info(String.format("Json Message received -> %s", user.toString()));
    }
}
