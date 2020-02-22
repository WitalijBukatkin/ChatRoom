package com.github.witalijbukatkin.chatroom.messageservice.service;

import com.github.witalijbukatkin.chatroom.messageservice.exception.NotFoundException;
import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import com.github.witalijbukatkin.chatroom.messageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message create(Message message, long chatId, String userId) {
        Assert.notNull(message, "message must not be null");
        Assert.notNull(userId, "userId must not be null");

        Message created = repository.save(message, chatId, userId);

        if (created == null) {
            throw new IllegalArgumentException();
        }

        return created;
    }

    public Message update(Message message, long chatId, String userId) {
        Assert.notNull(message, "message must not be null");
        Assert.notNull(message, "userId must not be null");

        Message updated = repository.save(message, chatId, userId);

        if (updated == null) {
            throw new NotFoundException();
        }

        return updated;
    }

    public void delete(long id, long chatId, String userId) {
        Assert.notNull(userId, "userId must not be null");

        if (!repository.delete(id, chatId, userId)) {
            throw new NotFoundException();
        }
    }

    public Message get(long id, long chatId, String userId) {
        Assert.notNull(userId, "userId must not be null");

        Message message = repository.get(id, chatId, userId);

        if (message == null) {
            throw new NotFoundException();
        }

        return message;
    }

    public List<Message> getAll(String userId){
        Assert.notNull(userId, "userId must not be null");
        return repository.getAll(userId);
    }

    public List<Message> getAllOfChat(long chatId, String userId){
        Assert.notNull(userId, "userId must not be null");
        return repository.getAllOfChat(chatId, userId);
    }
}
