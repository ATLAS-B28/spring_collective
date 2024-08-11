package com.example.demo_rabbit.controller;

import com.example.demo_rabbit.dto.User;
import com.example.demo_rabbit.publisher.RMJProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MsgJsonController {

    private RMJProducer rmjProducer;

    public MsgJsonController(RMJProducer rmjProducer) {
        this.rmjProducer = rmjProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMsg(@RequestBody User user) {
        rmjProducer.sendJsonMsg(user);
        return ResponseEntity.ok("JSON Message sent to RabbitMQ...");
    }
}
