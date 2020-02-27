package com.github.witalijbukatkin.authservice;

import com.github.witalijbukatkin.authservice.model.User;

public class TestData {
    public static final User USER1 = new User(1L, "user1", "userpassword");
    public static final User USER2 = new User(2L, "user2", "userpassword");

    public static User getCopy(User user) {
        return new User(user.getUsername(), user.getPassword());
    }
}
