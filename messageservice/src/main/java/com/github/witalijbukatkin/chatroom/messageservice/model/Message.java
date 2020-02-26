package com.github.witalijbukatkin.chatroom.messageservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity
public class Message extends BaseEntity {
    @JsonProperty(access = WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @NotEmpty
    private String senderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotEmpty
    private String data;

    public Message(@NotNull Long id, Chat chat, @NotEmpty String senderId, @NotNull Type type, @NotEmpty String data) {
        super(id);

        this.chat = chat;
        this.senderId = senderId;
        this.type = type;
        this.data = data;
    }

    public Message(Chat chat, @NotEmpty String senderId, @NotNull Type type, @NotEmpty String data) {
        this(null, chat, senderId, type, data);
    }

    public Message(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(chat, message.chat) &&
                Objects.equals(senderId, message.senderId) &&
                type == message.type &&
                Objects.equals(data, message.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat, senderId, type, data);
    }

    @Override
    public String toString() {
        return "Message{" +
                "chat=" + chat +
                ", senderId='" + senderId + '\'' +
                ", type=" + type +
                ", data='" + data + '\'' +
                '}';
    }
}
