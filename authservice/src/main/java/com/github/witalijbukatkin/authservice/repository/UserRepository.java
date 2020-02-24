package com.github.witalijbukatkin.authservice.repository;

import com.github.witalijbukatkin.authservice.model.User;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends Repository<User, Long> {
    User getUserByUsername(String name);

    @Transactional
    User save(User user);
}
