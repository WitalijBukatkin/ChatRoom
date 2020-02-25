package com.github.witalijbukatkin.chatroom.messageservice.controller;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import com.github.witalijbukatkin.chatroom.messageservice.service.MessageService;
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
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = MessageController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    public static final String REST_URL = "/rest/chats/{chatId}/messages";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Message> getAll(Principal user, @PathVariable long chatId) {
        log.info("getAll for userName {}", user.getName());
        return service.getAllOfChat(chatId, user.getName());
    }

    @GetMapping("/{id}")
    public Message get(Principal user, @PathVariable long id, @PathVariable long chatId) {
        log.info("get {} for userName {}", id, user.getName());
        return service.get(id, chatId, user.getName());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Message> create(Principal user, @Valid @RequestBody Message message, @PathVariable long chatId) {
        log.info("create {} for userName {}", message, user.getName());

        Message created = service.create(message, chatId, user.getName());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + message.getId())
                .build().toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Principal user, @PathVariable long id, @PathVariable long chatId) {
        log.info("delete {} for userName {}", id, user.getName());
        service.delete(id, chatId, user.getName());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(Principal user, @Valid @RequestBody Message message, @PathVariable long chatId) {
        log.info("update {} for userName {}", message, user.getName());
        service.update(message, chatId, user.getName());
    }
}