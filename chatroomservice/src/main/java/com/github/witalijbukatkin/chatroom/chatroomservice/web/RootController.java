package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RootController {

    @GetMapping("/")
    public String root(Principal user) {
        if (user == null) {
            return "login";
        }

        return "chatroom";
    }

    @PostMapping("/")
    public String rootPost(Principal user) {
        if (user == null) {
            return "forward:/login";
        }

        return "chatroom";
    }
}
