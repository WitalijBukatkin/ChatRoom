package com.github.witalijbukatkin.chatroom.chatroomservice.model;

import java.util.List;
import java.util.Objects;

public class Chat extends BaseEntity {
    private String name;

    private List<String> users;

    public Chat(Long id, String name, List<String> users) {
        super(id);
        this.name = name;
        this.users = users;
    }

    public Chat(String name, List<String> users) {
        this(null, name, users);
    }

    public Chat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(name, chat.name) &&
                Objects.equals(users, chat.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }
}
