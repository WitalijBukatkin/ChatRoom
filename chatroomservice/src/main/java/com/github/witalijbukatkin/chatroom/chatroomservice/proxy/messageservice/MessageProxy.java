package com.github.witalijbukatkin.chatroom.chatroomservice.proxy.messageservice;

import com.github.witalijbukatkin.chatroom.chatroomservice.to.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "messageservice-messageproxy", url = "${MESSAGESERVICE_URL}")
@RequestMapping("/rest/chats/{chatId}/messages")
public interface MessageProxy {
    @GetMapping
    List<Message> getAll(@PathVariable long chatId, @RequestHeader("Authorization") String userId);

    @GetMapping("/{id}")
    Message get(@PathVariable long id, @PathVariable long chatId, @RequestHeader("Authorization") String userId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<Message> create(@RequestBody Message message, @PathVariable long chatId, @RequestHeader("Authorization") String userId);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable long chatId, @PathVariable long id, @RequestHeader("Authorization") String userId);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable long chatId, @RequestBody Message message, @RequestHeader("Authorization") String userId);
}
