package com.github.witalijbukatkin.chatroom.messageservice.repository.datajpa;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudChatRepository extends JpaRepository<Chat, Long> {

    @Override
    @Transactional
    void delete(Chat chat);

    @Override
    @Transactional
    Chat save(Chat chat);

    @Query("from Chat where :userId in elements(users)")
    List<Chat> findAll(String userId);

    @Query("from Chat where id=:id and :userId in elements(users)")
    Chat isExistUserInChat(long id, String userId);
}
