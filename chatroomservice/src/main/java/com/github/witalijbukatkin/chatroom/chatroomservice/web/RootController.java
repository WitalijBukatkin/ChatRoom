package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(@RequestParam(required = false) String userId) {
        if (userId == null) {
            return "redirect:login";
        }

        return "redirect:chats?userId=" + userId;
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return "redirect:chats?userId=" + userId;
        }

        return "login";
    }
}
