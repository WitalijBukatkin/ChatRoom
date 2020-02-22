package com.github.witalijbukatkin.chatroom.chatroomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.github.witalijbukatkin.chatroom.chatroomservice")
public class ChatroomServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomServiceApplication.class, args);
    }

}
