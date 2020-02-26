package com.github.witalijbukatkin.chatroom.messageservice.service.inmemory;

import com.github.witalijbukatkin.chatroom.messageservice.exception.NotFoundException;
import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.repository.ChatRepository;
import com.github.witalijbukatkin.chatroom.messageservice.repository.inmemory.InMemoryChatRepositoryImpl;
import com.github.witalijbukatkin.chatroom.messageservice.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.github.witalijbukatkin.chatroom.messageservice.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = InMemoryChatRepositoryImpl.class)
class InMemoryChatRepositoryTest {
    private final ChatService service;

    @Autowired
    InMemoryChatRepositoryTest(@Qualifier("inMemoryChatRepositoryImpl") ChatRepository repository) {
        this.service = new ChatService(repository);
    }

    @Test
    void create() {
        Chat expected = service.create(
                new Chat("User3, User5", Arrays.asList(USER3, USER2)), USER2);
        assertNotNull(expected);

        Chat actual = service.get(expected.getId(), USER2);
        assertEquals(expected, actual);

        assertDoesNotThrow(() -> service.delete(actual.getId(), USER2));
    }

    @Test
    void createWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null, null));
    }

    @Test
    void update() {
        Chat updated = getCopy(CHAT1);
        assertNotNull(service.create(updated, USER1));

        updated.setName("New name");
        assertNotNull(service.update(updated, USER1));

        assertEquals(updated, service.get(updated.getId(), USER1));
        assertDoesNotThrow(() -> service.delete(updated.getId(), USER1));
    }

    @Test
    void updateWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.update(null, null));
    }

    @Test
    void delete() {
        Chat copy = getCopy(CHAT1);
        assertNotNull(service.create(copy, USER1));

        assertDoesNotThrow(() -> service.delete(copy.getId(), USER1));
    }

    @Test
    void deleteWithNotExist() {
        Chat copy = getCopy(CHAT1);
        copy.setId(12L);

        assertThrows(NotFoundException.class, () -> service.delete(copy.getId(), USER1));
    }

    @Test
    void deleteWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.delete(0, null));
    }

    @Test
    void get() {
        Chat actual = service.get(CHAT1.getId(), USER1);
        assertEquals(CHAT1, actual);
    }

    @Test
    void getWithNotFound() {
        Chat copy = getCopy(CHAT1);
        copy.setId(12L);

        assertThrows(NotFoundException.class, () -> service.get(copy.getId(), USER1));
    }

    @Test
    void getWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.get(CHAT1.getId(), null));
    }

    @Test
    void getAll() {
        List<Chat> expected = List.of(CHAT1, CHAT2);
        assertIterableEquals(expected, service.getAll(USER1));
    }

    @Test
    void getAllWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.getAll(null));
    }

    @Test
    void bindUser(){
        assertDoesNotThrow(() -> service.bindUser(CHAT1.getId(), USER1, USER3));

        Chat chat = service.get(CHAT1.getId(), USER1);

        List<String> expected = List.of(USER1, USER2, USER3);
        assertIterableEquals(expected, chat.getUsers());

        assertDoesNotThrow(() -> service.unbindUser(CHAT1.getId(), USER1, USER3));
    }

    @Test
    void unbindUser(){
        assertDoesNotThrow(() -> service.bindUser(CHAT1.getId(), USER1, USER3));
        assertDoesNotThrow(() -> service.unbindUser(CHAT1.getId(), USER1, USER3));

        Chat chat = service.get(CHAT1.getId(), USER1);

        List<String> expected = List.of(USER1, USER2);
        assertIterableEquals(expected, chat.getUsers());
    }
}