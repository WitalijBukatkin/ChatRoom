package com.github.witalijbukatkin.chatroom.chatroomservice.to;

public class Message extends BaseTo {
    private Chat chat;

    private String senderId;

    private Type type;

    private String data;

    public Message(Long id, Chat chat, String senderId, Type type, String data) {
        super(id);

        this.chat = chat;
        this.senderId = senderId;
        this.type = type;
        this.data = data;
    }

    public Message(Chat chat, String senderId, Type type, String data) {
        this(null, chat, senderId, type, data);
    }

    public Message() {
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
