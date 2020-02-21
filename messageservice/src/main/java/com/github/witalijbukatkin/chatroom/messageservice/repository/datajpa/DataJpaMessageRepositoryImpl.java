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
    public Message save(Message message, String userId) {
        if (!message.isNew() && get(message.getId(), userId) == null ||
                !chatRepository.isExistUserInChat(message.getChat().getId(), userId)) {
            return null;
        }
        return messageRepository.save(message);
    }

    @Override
    public boolean delete(long id, String userId) {
        Message message = get(id, userId);

        if (message == null || !chatRepository.isExistUserInChat(message.getChat().getId(), userId)) {
            return false;
        }

        messageRepository.delete(message);
        return true;
    }

    @Override
    public Message get(long id, String userId) {
        Optional<Message> message = messageRepository.findById(id);

        if(message.isEmpty() || !chatRepository.isExistUserInChat(message.get().getChat().getId(), userId)){
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
