package com.Personal.journalApp.service;

import com.Personal.journalApp.dto.ViewCountMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String message) {
        messagingTemplate.convertAndSend(
                "/topic/views",
                message
        );
    }
    public void publishViewCount(String journalId, int views) {

        System.out.println(
                "Publishing view count: " + journalId + " -> " + views
        );

        messagingTemplate.convertAndSend(
                "/topic/views",
                new ViewCountMessage(journalId, views)
        );
    }
}