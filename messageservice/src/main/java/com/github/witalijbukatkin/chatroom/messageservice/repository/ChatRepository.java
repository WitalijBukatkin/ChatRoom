package com.github.witalijbukatkin.chatroom.messageservice.repository;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;

import java.util.List;

public interface ChatRepository {
    Chat save(Chat chat, String userId);

    boolean delete(long id, String userId);

    Chat get(long id, String userId);

    List<Chat> getAll(String userId);

    boolean isExistUserInChat(long id, String userId);
}
