package com.github.witalijbukatkin.chatroom.messageservice.controller;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ChatController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChatController {
    public static final String REST_URL = "/rest/chats";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ChatService service;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    @GetMapping
    public List<Chat> getAll(@RequestParam String userId) {
        log.info("getAll for userId {}", userId);
        return service.getAll(userId);
    }

    @GetMapping("/{id}")
    public Chat get(@PathVariable long id, @RequestParam String userId) {
        log.info("get {} for userId {}", id, userId);
        return service.get(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Chat> create(@Valid @RequestBody Chat chat, @RequestParam String userId) {
        log.info("create {} for userId {}", chat, userId);

        Chat created = service.create(chat, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}?userId={userId}")
                .buildAndExpand(created.getId(), userId).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id, @RequestParam String userId) {
        log.info("delete {} for userId {}", id, userId);
        service.delete(id, userId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Chat chat, @RequestParam String userId) {
        log.info("update {} for userId {}", chat, userId);
        service.update(chat, userId);
    }

    @GetMapping("/bindUser/{newUserId}/to/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindUser(@PathVariable long chatId, @RequestParam String userId, @PathVariable String newUserId) {
        log.info("bindUser {} for userId {} to newUserId {}", chatId, userId, newUserId);
        service.bindUser(chatId, userId, newUserId);
    }

    @GetMapping("/unbindUser/{userId}/from/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbindUser(@PathVariable long chatId, @RequestParam String userId) {
        log.info("unbindUser {} for userId {}", chatId, userId);
        service.unbindUser(chatId, userId);
    }
}
