package com.github.witalijbukatkin.chatroom.chatroomservice.web;

import com.github.witalijbukatkin.chatroom.chatroomservice.service.UserService;
import com.github.witalijbukatkin.chatroom.chatroomservice.to.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, Model model, @RequestParam String username, @RequestParam String password) {
        log.info("login {}", username);

        try {
            String token = service.login(username, password);

            model.addAttribute("token", "Bearer " + token);
            model.addAttribute("userName", username);
            return "chatroom";
        } catch (RuntimeException | IOException e) {
            model.addAttribute("error", "User or password not correct!");
            return "login";
        }
    }

    @GetMapping("/regist")
    public String registration() {
        return "regist";
    }

    @PostMapping("/regist")
    public String registration(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword) {
        log.info("registration {}", username);

        try {
            service.register(username, password, repeatPassword);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "regist";
        }

        return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(User user) {
        if (user != null) {
            log.info("logout {}", user);
        }

        return "redirect:";
    }
}