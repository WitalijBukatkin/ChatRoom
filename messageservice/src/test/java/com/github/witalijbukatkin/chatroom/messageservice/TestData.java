package com.github.witalijbukatkin.chatroom.messageservice;

import com.github.witalijbukatkin.chatroom.messageservice.model.Chat;
import com.github.witalijbukatkin.chatroom.messageservice.model.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.witalijbukatkin.chatroom.messageservice.model.Type.TEXT;

public class TestData {

    public static final String USER1 = "user1";
    public static final String USER2 = "user2";
    public static final String USER3 = "user3";

    public static final Chat CHAT1 = new Chat(1L, "User1, User2",
            new ArrayList<>(List.of(USER1, USER2)));

    public static final Chat CHAT2 = new Chat(2L, "User1, User3",
            new ArrayList<>(List.of(USER1, USER3)));

    public static final Message MESSAGE1 = new Message(1L, CHAT1, USER1, TEXT, "Hello!");

    public static final Message MESSAGE2 = new Message(2L, CHAT2, USER3, TEXT, "Welcome to chat!");


    public static Chat getCopy(Chat chat) {
        return new Chat(chat.getName(), new ArrayList<>(chat.getUsers()));
    }

    public static Message getCopy(Message message){
        return new Message(message.getChat(), message.getSenderId(), message.getType(), message.getData());
    }
}
