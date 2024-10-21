package com.pragmatic.dao;

import com.pragmatic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Long id);
    Optional<User> findByNameAndEmail(String name, String email);
    List<User> findAll();
    User save(User user);
}
