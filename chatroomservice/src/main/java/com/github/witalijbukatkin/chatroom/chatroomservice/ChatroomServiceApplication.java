package com.github.witalijbukatkin.chatroom.chatroomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients("com.github.witalijbukatkin.chatroom.chatroomservice")
@EnableResourceServer
@RestController
public class ChatroomServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomServiceApplication.class, args);
    }
}
