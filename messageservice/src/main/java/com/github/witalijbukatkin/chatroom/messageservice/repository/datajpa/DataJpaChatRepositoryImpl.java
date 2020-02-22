package com.github.witalijbukatkin.chatroom.messageservice.repository.datajpa;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaChatRepositoryImpl implements ChatRepository {

    private final CrudChatRepository repository;

    @Autowired
    public DataJpaChatRepositoryImpl(CrudChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public Chat save(Chat chat, String userId) {
        if (!chat.isNew() && (get(chat.getId(), userId) == null ||
                !isExistUserInChat(chat.getId(), userId))) {
            return null;
        }
        return repository.save(chat);
    }

    @Override
    public boolean delete(long id, String userId) {
        Chat chat = get(id, userId);

        if (chat == null) {
            return false;
        }

        repository.delete(chat);
        return true;
    }

    @Override
    public Chat get(long id, String userId) {
        Optional<Chat> chat = repository.findById(id);

        if(chat.isEmpty() || !isExistUserInChat(chat.get().getId(), userId)){
            return null;
        }

        return chat.get();
    }

    @Override
    public List<Chat> getAll(String userId) {
        return repository.findAll(userId);
    }

    @Override
    public boolean isExistUserInChat(long id, String userId) {
        return repository.isExistUserInChat(id, userId) != null;
    }
}
