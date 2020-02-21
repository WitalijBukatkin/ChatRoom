package com.github.witalijbukatkin.chatroom.messageservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Message extends BaseEntity{
    @NotNull
    @ManyToOne
    private Chat chat;

    @NotEmpty
    private String senderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotEmpty
    private String data;

    public Message(@NotNull Long id, @NotNull Chat chat, @NotEmpty String senderId, @NotNull Type type, @NotEmpty String data) {
        super(id);

        this.chat = chat;
        this.senderId = senderId;
        this.type = type;
        this.data = data;
    }

    public Message(@NotNull Chat chat, @NotEmpty String senderId, @NotNull Type type, @NotEmpty String data) {
        this(null, chat, senderId, type, data);
    }

    public Message(){
    }

    public Chat getChat() {
        return chat;
    }

    public void setChatId(Chat chat) {
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
