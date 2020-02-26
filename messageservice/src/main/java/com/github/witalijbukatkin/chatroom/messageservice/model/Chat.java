package com.github.witalijbukatkin.chatroom.messageservice.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
public class Chat extends BaseEntity{
    @NotEmpty
    private String name;

    @NotEmpty
    @ElementCollection
    private List<String> users;

    public Chat(Long id, @NotEmpty String name, @NotEmpty List<String> users) {
        super(id);
        this.name = name;
        this.users = users;
    }

    public Chat(@NotEmpty String name, @NotEmpty List<String> users) {
        this(null, name, users);
    }

    public Chat(){
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

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
