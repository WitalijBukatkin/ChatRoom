package com.github.witalijbukatkin.chatroom.messageservice.repository.datajpa;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import com.github.witalijbukatkin.chatroom.messageservice.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMessageRepositoryImpl implements MessageRepository {
    private final CrudMessageRepository messageRepository;
    private final DataJpaChatRepositoryImpl chatRepository;

    public DataJpaMessageRepositoryImpl(CrudMessageRepository messageRepository, DataJpaChatRepositoryImpl chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public Message save(Message message, long chatId, String userId) {
        if (!message.isNew() && get(message.getId(), chatId, userId) == null ||
                !chatRepository.isExistUserInChat(chatId, userId)) {
            return null;
        }

        return messageRepository.save(message);
    }

    @Override
    public boolean delete(long id, long chatId, String userId) {
        Message message = get(id, chatId, userId);

        if (message == null) {
            return false;
        }

        messageRepository.delete(message);
        return true;
    }

    @Override
    public Message get(long id, long chatId, String userId) {
        Optional<Message> message = messageRepository.findById(id);

        if (message.isEmpty() || !chatRepository.isExistUserInChat(chatId, userId)) {
            return null;
        }

        return message.get();
    }

    @Override
    public List<Message> getAll(String userId) {
        return messageRepository.findAll(userId);
    }

    @Override
    public List<Message> getAllOfChat(long chatId, String userId) {
        return messageRepository.findAllOfChat(chatId, userId);
    }
}
