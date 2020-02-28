package com.github.witalijbukatkin.chatroom.messageservice.service.inmemory;

import com.github.witalijbukatkin.chatroom.messageservice.exception.NotFoundException;
import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import com.github.witalijbukatkin.chatroom.messageservice.repository.MessageRepository;
import com.github.witalijbukatkin.chatroom.messageservice.repository.inmemory.InMemoryChatRepositoryImpl;
import com.github.witalijbukatkin.chatroom.messageservice.repository.inmemory.InMemoryMessageRepositoryImpl;
import com.github.witalijbukatkin.chatroom.messageservice.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.witalijbukatkin.chatroom.messageservice.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {InMemoryMessageRepositoryImpl.class, InMemoryChatRepositoryImpl.class})
class InMemoryMessageRepositoryTest {
    private final MessageService service;

    @Autowired
    InMemoryMessageRepositoryTest(@Qualifier("inMemoryMessageRepositoryImpl") MessageRepository repository) {
        this.service = new MessageService(repository);
    }

    @Test
    void create() {
        Message expected = service.create(getCopy(MESSAGE1), CHAT1.getId(), USER1);
        assertNotNull(expected);

        Message actual = service.get(expected.getId(), CHAT1.getId(), USER1);
        assertEquals(expected, actual);

        assertDoesNotThrow(() -> service.delete(actual.getId(), CHAT1.getId(), USER1));
    }

    @Test
    void createWithUserFalse() {
        assertThrows(IllegalArgumentException.class, () -> service.create(MESSAGE1, CHAT1.getId(), USER3));
    }

    @Test
    void createWithTagOnMessage() {
        Message message = getCopy(MESSAGE1);
        message.setData("<tag></tag>");
        assertThrows(IllegalArgumentException.class, () -> service.create(message, CHAT1.getId(), USER3));
    }

    @Test
    void createWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null, 0, null));
    }

    @Test
    void update() {
        Message updated = getCopy(MESSAGE1);
        assertNotNull(service.create(updated, CHAT1.getId(), USER1));

        updated.setData("New name");
        assertNotNull(service.update(updated, CHAT1.getId(), USER1));

        assertEquals(updated, service.get(updated.getId(), CHAT1.getId(), USER1));
        assertDoesNotThrow(() -> service.delete(updated.getId(), CHAT1.getId(), USER1));
    }

    @Test
    void updateWithUserFalse(){
        assertThrows(NotFoundException.class, () -> service.update(MESSAGE1, CHAT1.getId(), USER3));
    }

    @Test
    void updateWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.update(null, 0, null));
    }

    @Test
    void delete() {
        Message copy = getCopy(MESSAGE1);
        assertNotNull(service.create(copy, CHAT1.getId(), USER1));

        assertDoesNotThrow(() -> service.delete(copy.getId(), CHAT1.getId(), USER1));
    }

    @Test
    void deleteWithUserFalse(){
        assertThrows(NotFoundException.class, () -> service.delete(MESSAGE1.getId(), CHAT2.getId(), USER3));
    }

    @Test
    void deleteWithNotExist() {
        Message copy = getCopy(MESSAGE1);
        copy.setId(12L);

        assertThrows(NotFoundException.class, () -> service.delete(copy.getId(), CHAT1.getId(), USER3));
    }

    @Test
    void deleteWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.delete(0, 0, null));
    }

    @Test
    void get() {
        Message actual = service.get(MESSAGE1.getId(), CHAT1.getId(), USER1);
        assertEquals(MESSAGE1, actual);
    }

    @Test
    void getWithUserFalse(){
        assertThrows(NotFoundException.class, () -> service.get(MESSAGE1.getId(), CHAT1.getId(), USER3));
    }

    @Test
    void getWithNotFound() {
        Message copy = getCopy(MESSAGE1);
        copy.setId(12L);

        assertThrows(NotFoundException.class, () -> service.get(copy.getId(), CHAT1.getId(), USER1));
    }

    @Test
    void getWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.get(0, CHAT1.getId(), null));
    }

    @Test
    void getAll() {
        List<Message> expected = List.of(MESSAGE1, MESSAGE2);
        assertIterableEquals(expected, service.getAll(USER1));
    }

    @Test
    void getAllWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.getAll(null));
    }

    @Test
    void getAllOfChat() {
        List<Message> expected = List.of(MESSAGE2);
        assertIterableEquals(expected, service.getAllOfChat(CHAT2.getId(), USER3));
    }

    @Test
    void getAllOfChatWithUserFalse(){
        assertThrows(NotFoundException.class, () -> service.getAllOfChat(CHAT2.getId(), USER2));
    }

    @Test
    void getAllOfChatWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.getAllOfChat(0, null));
    }
}