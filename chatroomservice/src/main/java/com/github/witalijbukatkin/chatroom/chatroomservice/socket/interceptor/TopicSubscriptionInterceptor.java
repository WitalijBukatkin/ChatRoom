package com.github.witalijbukatkin.chatroom.chatroomservice.socket.interceptor;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.ChatProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TopicSubscriptionInterceptor implements ChannelInterceptor {

    private static Logger logger = LoggerFactory.getLogger(TopicSubscriptionInterceptor.class);

    private final ChatProxy chatProxy;
    private final UserService service;

    @Autowired
    public TopicSubscriptionInterceptor(ChatProxy chatProxy, UserService service) {
        this.chatProxy = chatProxy;
        this.service = service;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            String destination = headerAccessor.getDestination();

            if (destination != null) {

                try {

                    long chatId = Long.parseLong(destination.substring(destination.lastIndexOf("/") + 1));

                    String token = ((OAuth2AuthenticationDetails) ((OAuth2Authentication)
                            headerAccessor.getHeader("simpUser")).getDetails()).getTokenValue();

                    String btoken = "Bearer " + token;

                    if (chatProxy.get(chatId, btoken) == null) {
                        throw new IllegalArgumentException("Not access to channel");
                    }

                    Map<String, Object> attributes = headerAccessor.getSessionAttributes();
                    attributes.put("user", service.getFromToken(btoken));
                    attributes.put("btoken", btoken);

                    logger.info("SUBSCRIBE TO {} WITH USERNAME {}", headerAccessor.getDestination(), token);

                    return message;
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }

            throw new IllegalArgumentException("Not access to channel");
        }

        return message;
    }
}