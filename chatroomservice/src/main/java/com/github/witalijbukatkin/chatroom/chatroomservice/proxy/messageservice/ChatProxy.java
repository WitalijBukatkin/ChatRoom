package com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice;

import com.github.witalijbukatkin.chatroom.chatroomservice.to.Chat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "messageservice-chatproxy", url = "192.168.1.70:8081")
@RequestMapping("/rest/chats")
public interface ChatProxy {
    @GetMapping
    List<Chat> getAll(@RequestParam String userId);

    @GetMapping("/{id}")
    Chat get(@PathVariable long id, @RequestParam String userId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<Chat> create(@RequestBody Chat chat, @RequestParam String userId);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable long id, @RequestParam String userId);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Chat chat, @RequestParam String userId);

    @GetMapping("/bindUser/{newUserId}/to/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void bindUser(@PathVariable long chatId, @RequestParam String userId, @PathVariable String newUserId);

    @GetMapping("/unbindUser/{userId}/from/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unbindUser(@PathVariable long chatId, @RequestParam String userId);
}
