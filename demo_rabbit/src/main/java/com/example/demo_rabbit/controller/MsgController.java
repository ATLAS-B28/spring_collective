package com.example.demo_rabbit.controller;

import com.example.demo_rabbit.publisher.RMProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MsgController {

    //inject the producer class
    private RMProducer rmProducer;

    public MsgController(RMProducer rmProducer) {
        this.rmProducer = rmProducer;
    }

    //localhost:8080/api/v1/publisher?msg=hello
    //this is where we request and query for a particular message,
    //so we use request parameter that is after "?" key-value
    //pairs are brought
    @GetMapping("/publish")
    public ResponseEntity<String> sendMsg(@RequestParam("msg") String msg) {

        //send the message to rabbitmq
        rmProducer.sendMessage(msg);
        return ResponseEntity.ok("Message sent to RabbitMQ...");
    }

}
