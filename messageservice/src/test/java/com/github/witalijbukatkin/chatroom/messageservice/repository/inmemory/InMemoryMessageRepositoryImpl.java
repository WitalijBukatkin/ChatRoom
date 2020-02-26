package com.github.witalijbukatkin.chatroom.messageservice.repository.inmemory;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import com.github.witalijbukatkin.chatroom.messageservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.github.witalijbukatkin.chatroom.messageservice.TestData.MESSAGE1;
import static com.github.witalijbukatkin.chatroom.messageservice.TestData.MESSAGE2;

@Repository
public class InMemoryMessageRepositoryImpl implements MessageRepository {
    private static AtomicLong counter = new AtomicLong(100);

    private final Map<Long, Message> messages = new ConcurrentHashMap<>();

    private final InMemoryChatRepositoryImpl chatRepository;

    @Autowired
    public InMemoryMessageRepositoryImpl(InMemoryChatRepositoryImpl chatRepository) {
        this.chatRepository = chatRepository;

        messages.put(MESSAGE1.getId(), MESSAGE1);
        messages.put(MESSAGE2.getId(), MESSAGE2);
    }

    @Override
    public Message save(Message message, long chatId, String userId) {
        if (!chatRepository.isExistUserInChat(chatId, userId)) {
            return null;
        }

        message.setChat(chatRepository.get(chatId, userId));

        if (message.isNew()) {
            message.setId(counter.getAndIncrement());
            messages.put(message.getId(), message);
            return message;
        }

        return messages.computeIfPresent(message.getId(), (id, To) -> message);
    }

    @Override
    public boolean delete(long id, long chatId, String userId) {
        Message message = get(id, chatId, userId);

        if (message == null || !message.getSenderId().equals(userId)) {
            return false;
        }

        return messages.remove(id, message);
    }

    @Override
    public Message get(long id, long chatId, String userId) {
        Message message = messages.get(id);

        if (message == null || !chatRepository.isExistUserInChat(chatId, userId)) {
            return null;
        }

        return message;
    }

    @Override
    public List<Message> getAll(String userId) {
        List<Message> returnMessages = new ArrayList<>(messages.values());

        return returnMessages.stream()
                .filter(message -> chatRepository.isExistUserInChat(message.getChat().getId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getAllOfChat(long chatId, String userId) {
        if (!chatRepository.isExistUserInChat(chatId, userId)) {
            return null;
        }

        return new ArrayList<>(messages.values()).stream()
                .filter(message -> message.getChat().getId() == chatId)
                .collect(Collectors.toList());
    }
}
