package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.authservice.UserProxy;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Base64;

@Controller
public class UserController {
    private final UserProxy proxy;

    @Autowired
    public UserController(UserProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/login")
    public String getToken() throws IOException {
        String encoding = Base64.getEncoder().encodeToString("service:secret".getBytes());

        String json = proxy.getToken("Basic " + encoding, "password", "test", "test");

        String token = new ObjectMapper().readTree(json).get("access_token").asText();

        return ""
    }
}