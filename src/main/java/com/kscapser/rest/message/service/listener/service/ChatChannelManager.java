package com.kscapser.rest.message.service.listener.service;

import com.kscapser.rest.message.service.listener.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatChannelManager {

    public void execute(final String messageKey, Object chatMessage) {

        log.info("MessageKey : {} ", messageKey);
        log.info("ChatMessage : {} ",chatMessage);
    }
}
