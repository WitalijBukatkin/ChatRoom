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
import java.security.Principal;
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
    public List<Chat> getAll(Principal user) {
        log.info("getAll for userName {}", user.getName());
        return service.getAll(user.getName());
    }

    @GetMapping("/{id}")
    public Chat get(Principal user, @PathVariable long id) {
        log.info("get {} for userId {}", id, user.getName());
        return service.get(id, user.getName());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Chat> create(Principal user, @Valid @RequestBody Chat chat) {
        log.info("create {} for userName {}", chat, user.getName());

        Chat created = service.create(chat, user.getName());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + created.getId())
                .build().toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Principal user, @PathVariable long id) {
        log.info("delete {} for userName {}", id, user.getName());
        service.delete(id, user.getName());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(Principal user, @Valid @RequestBody Chat chat) {
        log.info("update {} for userName {}", chat, user.getName());
        service.update(chat, user.getName());
    }

    @GetMapping("/bindUser/{newUserId}/to/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bindUser(Principal user, @PathVariable long chatId, @PathVariable String newUserId) {
        log.info("bindUser {} for userName {} to newUserId {}", chatId, user.getName(), newUserId);
        service.bindUser(chatId, user.getName(), newUserId);
    }

    @GetMapping("/unbindUser/from/{chatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unbindUser(Principal user, @PathVariable long chatId) {
        log.info("unbindUser {} for userName {}", chatId, user.getName());
        service.unbindUser(chatId, user.getName());
    }
}
