package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class RootController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String root(Principal user) {
        if (user == null) {
            return "redirect:http://192.168.1.70:5000/login";
        }

        return "forward:/chats";
    }

    @PostMapping("/")
    public String receiveToken(HttpServletResponse response, @RequestParam(required = false) String accessToken) {
        if (accessToken == null) {
            return "redirect:http://192.168.1.70:5000/login";
        }

        log.info("receiveToken");

        response.setHeader("Authorization", "Bearer " + accessToken);
        return "chatroom";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:";
    }
}
