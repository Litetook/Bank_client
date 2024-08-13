package com.pragmatic.repository;


import com.pragmatic.dao.FileDataProvider;
import com.pragmatic.dao.UserConverter;
import com.pragmatic.model.User;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
@Repository
public class UserRepository implements IUserRepository {
    private Integer lastId = 0;
    private Map<Integer, User> users;

    public UserRepository() {
        this.users = new HashMap<>();

        String[][] users = {
                                {"user1", "bla@bla.com", "testpass"},
                                {"user2", "bla2@bla.com", "testpass"},
                                {"user3", "bla3@bla.com", "testpass"},
                                {"user4", "bla4@bla.com", "testpass"},
                                {"user5", "bla5@bla.com", "testpass"},
                            };


        for (String[] i: users) {
                this.users.put(this.lastId, createUser(i[0], i[1], i[2]));
        }

        this.users.put(lastId, createUser("Pedro", "pedro@gmail.com", "blabla1"));

    }

    public User createUser(String name, String email, String password) {
        Integer userId = ++this.lastId;
        User newUser = new User();
        newUser.setName(name);
        newUser.setId(userId);
        newUser.setEmail(email);
        newUser.setPassword(password);
        this.users.put(userId, newUser);
        return newUser;
    }
    public List<User> getRepoList() {
        return this.users.values().stream().collect(Collectors.toList());
    }

    public User getUserById(Integer userId) {
        return this.users.get(userId);
    }


}