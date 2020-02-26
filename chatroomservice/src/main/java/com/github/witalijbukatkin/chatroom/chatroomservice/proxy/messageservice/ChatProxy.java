package com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice;

import com.github.witalijbukatkin.chatroom.chatroomservice.to.Chat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "messageservice-chatproxy", url = "${MESSAGESERVICE_URL}")
@RequestMapping("/rest/chats")
public interface ChatProxy {
    @GetMapping
    List<Chat> getAll(@RequestHeader("Authorization") String userId);

    @GetMapping("/{id}")
    Chat get(@PathVariable long id, @RequestHeader("Authorization") String userId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<Chat> create(@RequestBody Chat chat, @RequestHeader("Authorization") String userId);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable long id, @RequestHeader("Authorization") String userId);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Chat chat, @RequestHeader("Authorization") String userId);

    @GetMapping("/bindUser/{newUserId}/to/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void bindUser(@PathVariable long chatId, @RequestHeader("Authorization") String userId, @PathVariable String newUserId);

    @GetMapping("/unbindUser/from/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unbindUser(@PathVariable long chatId, @RequestHeader("Authorization") String userId);
}
