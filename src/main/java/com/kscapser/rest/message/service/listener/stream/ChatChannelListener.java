package com.kscapser.rest.message.service.listener.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kscapser.rest.message.service.listener.model.ChatMessage;
import com.kscapser.rest.message.service.listener.service.ChatChannelManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.ACKNOWLEDGMENT;
import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_MESSAGE_KEY;

@Component
@Slf4j
public class ChatChannelListener {

    private final ChatChannelManager chatChannelManager;

    @Autowired
    public ChatChannelListener(ChatChannelManager chatChannelManager) {
        this.chatChannelManager = chatChannelManager;
    }

    @KafkaListener(topics = "#{'${kscapser.kafka.producer.topics.channel}'.split(',')}", groupId = "input-group-1")
    public void listen(@Header (RECEIVED_MESSAGE_KEY) String messageKey,  @Payload String message){
        log.info("Start processing message key: {} message {} ",messageKey);
        try {
            //ChatMessage chatMessage = new ObjectMapper().readValue(message.getPayload().toString(), ChatMessage.class);
            chatChannelManager.execute(messageKey,  message);
           // acknowledgment.acknowledge();
        }catch (Exception e){
            log.error("Exception occurred : {}  ",e.getLocalizedMessage());
        }

    }
}
