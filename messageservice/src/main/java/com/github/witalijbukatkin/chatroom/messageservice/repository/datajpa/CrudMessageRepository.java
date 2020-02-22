package com.github.witalijbukatkin.chatroom.messageservice.repository.datajpa;

import com.github.witalijbukatkin.chatroom.messageservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMessageRepository extends JpaRepository<Message, Long> {

    @Override
    @Transactional
    void delete(Message message);

    @Override
    @Transactional
    Message save(Message message);

    @Query("from Message where chat in (from Chat where :userId in elements(users))")
    List<Message> findAll(String userId);

    @Query("from Message where chat in (from Chat where id = :chatId and :userId in elements(users))")
    List<Message> findAllOfChat(long chatId, String userId);
}
