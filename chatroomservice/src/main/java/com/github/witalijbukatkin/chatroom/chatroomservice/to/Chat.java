package com.github.witalijbukatkin.chatroom.chatroomservice.to;

import java.util.List;

public class Chat extends BaseTo {
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
}
