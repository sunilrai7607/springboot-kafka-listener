package com.kscapser.rest.message.service.listener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaMessageService {

    private final BinderAwareChannelResolver resolver;
    private String topicPattern = "sample.message.%s.events";

    @Autowired
    public KafkaMessageService(BinderAwareChannelResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * Method to generate the message to topic
     * @return
     */
    public String createMessage(String channelName) {

        Message<String> message = MessageBuilder.withPayload("Hello Sunil Rai, How are you ")
                .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID())
                .setHeader("type", "greeting")
                .build();
        log.info("Generated Message : {} ", message);
        this.resolver
                .resolveDestination(createTopic(channelName))
                .send(message);
        return "Message created Successfully";
    }

    private String createTopic(String channelName) {
        return String.format(topicPattern, channelName);
    }
}
