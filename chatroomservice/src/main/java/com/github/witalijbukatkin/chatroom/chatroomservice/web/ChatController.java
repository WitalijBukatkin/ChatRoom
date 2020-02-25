package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.ChatProxy;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ChatController {
    private final ChatProxy proxy;

    @Autowired
    public ChatController(ChatProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/chats")
    public String chats(Map<String, Object> model, @RequestParam(required = false) String userId) {
        model.put("chats", proxy.getAll(userId));
        model.put("userId", userId);
        return "chats";
    }

    @PostMapping("/chats")
    public String createChat(@RequestParam(required = false) String userId, @RequestParam String withUserId) {
        if (userId == null) {
            return "redirect:login";
        }

        String name = userId + ", " + withUserId;
        proxy.create(new Chat(name, List.of(userId, withUserId)), userId);

        return "redirect:chats?userId=" + userId;
    }
}
