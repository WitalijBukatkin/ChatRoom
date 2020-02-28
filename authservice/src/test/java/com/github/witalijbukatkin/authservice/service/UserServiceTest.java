package com.github.witalijbukatkin.authservice.service;

import com.github.witalijbukatkin.authservice.config.SecurityConfiguration;
import com.github.witalijbukatkin.authservice.model.User;
import com.github.witalijbukatkin.authservice.repository.InMemoryUserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.witalijbukatkin.authservice.TestData.USER1;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {UserService.class, InMemoryUserRepositoryImpl.class, SecurityConfiguration.class})
class UserServiceTest {
    private final UserService service;

    @Autowired
    UserServiceTest(UserService service) {
        this.service = service;
    }

    @Test
    void loadUserByUsername() {
        User actual = service.loadUserByUsername(USER1.getUsername());
        assertEquals(USER1, actual);
    }

    @Test
    void loadUserByUsernameWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.loadUserByUsername(null));
    }

    @Test
    void loadUserByUsernameWithUpperCase() {
        User actual = service.loadUserByUsername(USER1.getUsername().toUpperCase());
        assertEquals(USER1, actual);
    }

    @Test
    void register() {
        User expected = service.register(
                new User("user3", "password"));
        assertNotNull(expected);

        User actual = service.loadUserByUsername(expected.getUsername());
        assertEquals(expected, actual);

        //TODO: Does not delete!
    }

    @Test
    void registerWithUpperCaseName() {
        User expected = service.register(
                new User("user4".toUpperCase(), "password"));
        assertNotNull(expected);

        User actual = service.loadUserByUsername(expected.getUsername().toLowerCase());
        assertEquals(expected, actual);
    }

    @Test
    void registerWithNull() {
        assertThrows(IllegalArgumentException.class, () -> service.register(null));
    }
}