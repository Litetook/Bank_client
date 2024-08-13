package com.pragmatic.repository;

import com.pragmatic.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getRepoList();
    User createUser(String name, String email, String password);
    User getUserById(Integer userId);

}
