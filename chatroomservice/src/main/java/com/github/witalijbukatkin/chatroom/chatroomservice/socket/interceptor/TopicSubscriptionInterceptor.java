package com.github.witalijbukatkin.chatroom.chatroomservice.socket.interceptor;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.ChatProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class TopicSubscriptionInterceptor implements ChannelInterceptor {

    private static Logger logger = LoggerFactory.getLogger(TopicSubscriptionInterceptor.class);
    private final ChatProxy proxy;

    @Autowired
    public TopicSubscriptionInterceptor(ChatProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            String destination = headerAccessor.getDestination();

            if (destination != null) {
                long chatId = Long.parseLong(destination.substring(destination.lastIndexOf("/") + 1));
                String username = headerAccessor.getNativeHeader("username").get(0);

                proxy.get(chatId, username);

                logger.info("SUBSCRIBE TO {} WITH USERNAME {}", headerAccessor.getDestination(), username);

                headerAccessor.getSessionAttributes()
                        .put("username", username);
                return message;
            }

            throw new IllegalArgumentException("Not access to channel");
        }

        return message;
    }
}