package com.github.witalijbukatkin.chatroom.chatroomservice.to;

public abstract class BaseTo {
    private Long id;

    public BaseTo(Long id) {
        this.id = id;
    }

    public BaseTo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
