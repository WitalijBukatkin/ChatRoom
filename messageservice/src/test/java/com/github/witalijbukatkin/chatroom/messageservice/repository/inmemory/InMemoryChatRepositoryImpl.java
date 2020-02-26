package com.github.witalijbukatkin.chatroom.messageservice.repository.inmemory;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.repository.ChatRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.github.witalijbukatkin.chatroom.messageservice.TestData.CHAT1;
import static com.github.witalijbukatkin.chatroom.messageservice.TestData.CHAT2;

@Repository
public class InMemoryChatRepositoryImpl implements ChatRepository {
    private static AtomicLong counter = new AtomicLong(100);

    private final Map<Long, Chat> chats = new ConcurrentHashMap<>();

    public InMemoryChatRepositoryImpl() {
        chats.put(CHAT1.getId(), CHAT1);
        chats.put(CHAT2.getId(), CHAT2);
    }

    @Override
    public Chat save(Chat chat, String userId) {
        if (chat.getId() != null && !isExistUserInChat(chat.getId(), userId)) {
            return null;
        }

        if (chat.isNew()) {
            chat.setId(counter.getAndIncrement());
            chats.put(chat.getId(), chat);
            return chat;
        }

        return chats.computeIfPresent(chat.getId(), (id, To) -> chat);
    }

    @Override
    public boolean delete(long id, String userId) {
        Chat chat = get(id, userId);

        if (chat == null) {
            return false;
        }

        return chats.remove(id, chat);
    }

    @Override
    public Chat get(long id, String userId) {
        Chat chat = chats.get(id);

        if (chat == null || !isExistUserInChat(chat.getId(), userId)) {
            return null;
        }

        return chat;
    }

    @Override
    public List<Chat> getAll(String userId) {
        List<Chat> returnChats = new ArrayList<>(chats.values());

        return returnChats.stream()
                .filter(chat -> isExistUserInChat(chat.getId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExistUserInChat(long id, String userId) {
        Chat chat = chats.get(id);

        if(chat == null){
            return false;
        }

        return chat.getUsers().contains(userId);
    }
}
