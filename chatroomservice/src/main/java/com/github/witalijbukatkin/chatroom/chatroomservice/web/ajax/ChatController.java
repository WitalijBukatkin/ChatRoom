package com.github.witalijbukatkin.chatroom.chatroomservice.web.ajax;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.ChatProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/chats", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ChatProxy proxy;

    @Autowired
    public ChatController(ChatProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public List<Chat> chats(@RequestHeader("Authorization") String token) {
        log.info("getAll token {}", token);
        return proxy.getAll(token);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createChat(Principal user, @RequestHeader("Authorization") String token, @RequestParam String withUserId) {
        log.info("newChat with {} and {} token {}", user.getName(), withUserId, token);
        String name = user.getName() + ", " + withUserId;
        proxy.create(new Chat(name, List.of(user.getName(), withUserId)), token);
    }
}