package com.github.witalijbukatkin.chatroom.chatroomservice.web.ajax;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.MessageProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ajax/chats/{chatId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MessageProxy proxy;

    public MessageController(MessageProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public List<Message> messages(@RequestHeader("Authorization") String token, @PathVariable Long chatId) {
        log.info("getAll of chatId {} token {}", chatId, token);
        return proxy.getAll(chatId, token);
    }
}
