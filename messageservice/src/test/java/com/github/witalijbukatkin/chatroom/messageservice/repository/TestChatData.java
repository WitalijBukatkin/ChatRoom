package com.github.witalijbukatkin.chatroom.messageservice.repository;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;

import java.util.ArrayList;
import java.util.Arrays;

public class TestChatData {

    public static final String USER1 = "user1";
    public static final String USER2 = "user2";
    public static final String USER3 = "user3";

    public static final Chat CHAT1 = new Chat(1L, "User1, User2",
            Arrays.asList(USER1, USER2));

    public static final Chat CHAT2 = new Chat(2L, "User1, User3",
            Arrays.asList(USER1, USER3));


    public static Chat getCopy(Chat chat) {
        return new Chat(chat.getName(), new ArrayList<>(chat.getUsers()));
    }
}
