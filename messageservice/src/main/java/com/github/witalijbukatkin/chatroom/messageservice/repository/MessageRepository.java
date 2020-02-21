package com.github.witalijbukatkin.chatroom.messageservice.repository;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;

import java.util.List;

public interface MessageRepository {
    Message save(Message message, String userId);

    boolean delete(long id, String userId);

    Message get(long id, String userId);

    List<Message> getAll(String userId);

    List<Message> getAllOfChat(long chatId, String userId);
}
