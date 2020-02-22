package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import com.github.witalijbukatkin.chatroom.chatroomservice.model.Message;
import com.github.witalijbukatkin.chatroom.chatroomservice.model.Type;
import com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice.MessageProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/chats/{chatId}")
public class MessageController {
    private final MessageProxy proxy;

    public MessageController(MessageProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping
    public String messages(@PathVariable Long chatId, @RequestParam(required = false) String userId, Map<String, Object> model) {
        if (userId == null) {
            return "redirect:login";
        }

        System.out.println(chatId);

        model.put("messages", proxy.getAll(chatId, userId));
        model.put("userId", userId);

        return "messages";
    }

    @PostMapping
    public String createMessage(@RequestParam String senderId, @RequestParam String data, @PathVariable Long chatId) {
        proxy.create(new Message(null, senderId, Type.TEXT, data), chatId, senderId);

        return "redirect:/chats/" + chatId + "/?userId=" + senderId;
    }
}
