package com.kscapser.rest.message.service.listener.controller;

import com.kscapser.rest.message.service.listener.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageListenerController {

    private final KafkaMessageService kafkaMessageService;

    @Autowired
    public MessageListenerController(KafkaMessageService kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok(kafkaMessageService.createMessage("sample channel"));
    }
}
