package com.github.witalijbukatkin.chatroom.chatroomservice.web;

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

        model.put("messages", proxy.getAll(chatId, userId));
        model.put("userId", userId);
        model.put("chatId", chatId);

        return "messages";
    }

    @PostMapping
    public String createMessage(@PathVariable Long chatId, @RequestParam String senderId, @RequestParam String data) {
        return "redirect:/chats/" + chatId + "/?userId=" + senderId;
    }
}
