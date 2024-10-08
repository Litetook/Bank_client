package com.pragmatic.dao;

import com.pragmatic.dto.impl.UserDtoImpl;
import com.pragmatic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findById(Integer id);
    Optional<User> findByAttributes(UserDtoImpl userDto);
//    public Optional<User> findByNameAndEmail(String name,  String email);
    List<User> findAll();
    User save(User user);
}
