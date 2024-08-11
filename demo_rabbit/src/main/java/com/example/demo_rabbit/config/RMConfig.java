package com.example.demo_rabbit.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RMConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    //Queue bean
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    //Json queue bean
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    //Topic Exchange bean for both queues, sting and JSON message
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //Binding bean and bind queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    //binding bean and bind the JSON queue and exchange
    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    //Message converter bean
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    //Amqp template bean spring's connection factory to create connections
    //and pass in the converter and return the rabbitmq specific template.
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    //connection factory and rabbit template and rabbit admin

}
