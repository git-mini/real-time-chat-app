package com.realtime.chatApp.controller;

import com.realtime.chatApp.dto.ChatMessage;
import com.realtime.chatApp.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final RedisTemplate redisTemplate;

    // Add User to Application
    @MessageMapping("/chat.user")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {

        // Get userName from Chat Message object and adding it to the web socket session
        simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getUserName());

        chatMessage.setMessageType(MessageType.JOIN);
        chatMessage.setMessage(chatMessage.getUserName() + " joined the chat!");
        chatMessage.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        log.info("User joined : {} ", chatMessage.getUserName());

        // Sending chat Message back to Clients with Message type as JSON
        redisTemplate.convertAndSend("chat", chatMessage);
        return chatMessage;
    }

    // Send Message to the Clients
    @MessageMapping("/chat.send")
    public ChatMessage sendChatMessage(@Payload ChatMessage chatMessage) {

        // Add Logic to send message to Redis DB Queue
        chatMessage.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        redisTemplate.convertAndSend("chat", chatMessage);

        return chatMessage;
    }
}
