package com.github.witalijbukatkin.authservice.service;

import com.github.witalijbukatkin.authservice.model.User;
import com.github.witalijbukatkin.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "userName must not be null");
        return userRepository.getUserByUsername(username.toLowerCase());
    }

    public User register(User user) {
        Assert.notNull(user, "user must not be null");
        User existing = userRepository.getUserByUsername(user.getUsername().toLowerCase());

        if(existing!=null) {
            throw new IllegalArgumentException("user already exists: " + existing.getUsername().toLowerCase());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
