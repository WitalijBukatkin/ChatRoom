package com.github.witalijbukatkin.authservice.repository;

import com.github.witalijbukatkin.authservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.github.witalijbukatkin.authservice.TestData.USER1;
import static com.github.witalijbukatkin.authservice.TestData.USER2;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static AtomicLong counter = new AtomicLong(100);

    private final Map<Long, User> users = new ConcurrentHashMap<>();

    public InMemoryUserRepositoryImpl() {
        users.put(USER1.getId(), USER1);
        users.put(USER2.getId(), USER2);
    }

    @Override
    public User getUserByUsername(String name) {
        Optional<User> userOptional = users.values().stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst();
        return userOptional.orElse(null);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.getAndIncrement());
            users.put(user.getId(), user);
            return user;
        }

        return users.computeIfPresent(user.getId(), (id, To) -> user);
    }
}
