package com.github.witalijbukatkin.chatroom.chatroomservice.proxy.authservice;

import com.github.witalijbukatkin.chatroom.chatroomservice.to.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authservice", url = "192.168.1.70:5000")
@RequestMapping("/oauth")
public interface UserProxy {

    @PostMapping("/register")
    User register(@RequestBody User user);

    @PostMapping("/token")
    String getToken(@RequestHeader("Authorization") String code,
                    @RequestParam String grant_type,
                    @RequestParam String username,
                    @RequestParam String password);
}
