package com.github.witalijbukatkin.chatroom.chatroomservice.socket;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.MessageProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.Message;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;


@Controller
public class MessageWebSocketController {
    private final SimpMessagingTemplate template;
    private final MessageProxy proxy;

    @Autowired
    public MessageWebSocketController(SimpMessagingTemplate template, MessageProxy proxy) {
        this.template = template;
        this.proxy = proxy;
    }

    @MessageMapping("/{chatId}")
    public void sendMessage(@DestinationVariable Long chatId, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();

        User user = (User) sessionAttributes.get("user");
        String btoken = (String) sessionAttributes.get("btoken");

        if (user == null) {
            throw new IllegalArgumentException();
        }

        message.setSenderId(user.getUsername());

        proxy.create(message, chatId, btoken);

        template.convertAndSend("/topic/" + chatId, message);
    }
}
