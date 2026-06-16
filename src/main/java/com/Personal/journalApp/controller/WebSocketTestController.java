package com.Personal.journalApp.controller;

import com.Personal.journalApp.service.WebSocketPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class WebSocketTestController {

    private final WebSocketPublisher publisher;

    public WebSocketTestController(WebSocketPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/broadcast")
    public String broadcast() {

        publisher.sendMessage("Hello from WebSocket");

        return "Message sent";
    }
}