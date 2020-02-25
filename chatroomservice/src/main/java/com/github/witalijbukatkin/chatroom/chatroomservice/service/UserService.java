package com.github.witalijbukatkin.chatroom.chatroomservice.service;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.authservice.UserProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserService {
    private final UserProxy proxy;

    @Autowired
    public UserService(UserProxy proxy) {
        this.proxy = proxy;
    }

    public String login(String username, String password) throws IOException {
        String encoding = Base64.getEncoder().encodeToString("service:secret".getBytes());

        String json = proxy.getToken("Basic " + encoding,
                "password", username, password);

        return new ObjectMapper().readTree(json).get("access_token").asText();
    }

    public void register(String username, String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords not equals!");
        }

        proxy.register(new User(username, password));
    }

    public User getFromToken(String btoken) throws IOException {
        String json = proxy.getCurrent(btoken);
        JsonNode node = new ObjectMapper().readTree(json);

        return new User(node.get("name").asText());
    }
}
