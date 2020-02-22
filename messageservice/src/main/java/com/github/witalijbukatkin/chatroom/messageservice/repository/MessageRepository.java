package com.github.witalijbukatkin.chatroom.messageservice.repository;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;

import java.util.List;

public interface MessageRepository {
    Message save(Message message, long chatId, String userId);

    boolean delete(long id, long chatId, String userId);

    Message get(long id, long chatId, String userId);

    List<Message> getAll(String userId);

    List<Message> getAllOfChat(long chatId, String userId);
}
