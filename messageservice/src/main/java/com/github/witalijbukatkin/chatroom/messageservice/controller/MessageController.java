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
import java.util.List;

@RestController
@RequestMapping(value = MessageController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    public static final String REST_URL = "/rest/messages";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Message> getAll(@RequestParam String userId) {
        log.info("getAll for userId {}", userId);
        return service.getAll(userId);
    }

    @GetMapping("/{id}")
    public Message get(@PathVariable long id, @RequestParam String userId) {
        log.info("get {} for userId {}", id, userId);
        return service.get(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Message> create(@Valid @RequestBody Message message, @RequestParam String userId) {
        log.info("create {} for userId {}", message, userId);

        Message created = service.create(message, userId);

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
    public void update(@Valid @RequestBody Message message, @RequestParam String userId) {
        log.info("update {} for userId {}", message, userId);
        service.update(message, userId);
    }

    @GetMapping("/of/{chatId}")
    public List<Message> getAllOfChat(@PathVariable long chatId, @RequestParam String userId) {
        log.info("getAllOfChat {} for userId {}", chatId, userId);
        return service.getAllOfChat(chatId, userId);
    }
}