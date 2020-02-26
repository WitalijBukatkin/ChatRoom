package com.github.witalijbukatkin.chatroom.messageservice.service;

import com.github.witalijbukatkin.chatroom.messageservice.exception.NotFoundException;
import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository repository;

    @Autowired
    public ChatService(@Qualifier("dataJpaChatRepositoryImpl") ChatRepository repository) {
        this.repository = repository;
    }

    public Chat create(Chat chat, String userId){
        Assert.notNull(chat, "chat must not be null");
        Assert.notNull(userId, "userId must not be null");
        Chat created = repository.save(chat, userId);

        if(created == null) {
            throw new IllegalArgumentException();
        }

        return created;
    }

    public Chat update(Chat chat, String userId){
        Assert.notNull(chat, "chat must not be null");
        Assert.notNull(userId, "userId must not be null");
        Chat updated = repository.save(chat, userId);

        if(updated == null) {
            throw new NotFoundException();
        }

        return updated;
    }

    public void delete(long id, String userId){
        Assert.notNull(userId, "userId must not be null");
        if(!repository.delete(id, userId)) {
            throw new NotFoundException();
        }
    }

    public Chat get(long id, String userId){
        Assert.notNull(userId, "userId must not be null");
        Chat chat = repository.get(id, userId);

        if(chat == null){
            throw new NotFoundException();
        }

        return chat;
    }

    public List<Chat> getAll(String userId){
        Assert.notNull(userId, "userId must not be null");
        return repository.getAll(userId);
    }

    public void bindUser(long id, String userId, String newUserId){
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(newUserId, "newUserId must not be null");
        Chat chat = get(id, userId);

        chat.getUsers().add(newUserId);

        repository.save(chat, userId);
    }

    public void unbindUser(long id, String userId){
        Assert.notNull(userId, "userId must not be null");
        Chat chat = get(id, userId);

        if(chat.getUsers().remove(userId)){
            throw new NotFoundException();
        }
    }
}
