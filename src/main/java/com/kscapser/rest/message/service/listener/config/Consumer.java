package com.kscapser.rest.message.service.listener.config;


import com.kscapser.rest.message.service.listener.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@EnableBinding(Sink.class)
@Slf4j
public class Consumer {

    @StreamListener(target = Sink.INPUT)
    public void consume(String message){
        log.info("Received message : {} ",message);
    }

//    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='chat'")
//    public void handler(@Payload ChatMessage chatMessage){
//        final DateTimeFormatter df = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)
//                .withZone(ZoneId.systemDefault());
//        final String time = df.format(Instant.ofEpochMilli(chatMessage.getTime()));
//        log.info("recieved a complex message : [{}]: {}", time, chatMessage.getContents());
//    }

}
