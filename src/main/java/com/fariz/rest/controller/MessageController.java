package com.fariz.rest.controller;

import com.fariz.rest.model.InputMessageModel;
import com.fariz.rest.model.OutputMessageModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/")
    @SendTo("/chat/sendMessage")
    public OutputMessageModel sendMessage(InputMessageModel message) {
        return new OutputMessageModel("New message " + message.getMessage());
    }

}

