package main.java.com.pragmatic.repository;


import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.User;

import java.util.*;

public class UserRepository implements IRepository {
    private static String tableImportName = "users.csv";
    private Integer lastId = 0;
    private Map<Integer, User> users;
    private Map<Integer, User> userLinesMap;
    private FileDataProvider file= new FileDataProvider(tableImportName);
    private List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "name", "email","password"));

    public UserRepository() {
        this.users = new HashMap<>();
        this.userLinesMap = new HashMap<>();
//        Init даних з файлу
        List<User> userList = this.file.initUserData();
        userList.forEach(user -> {
                    if ( this.users.containsKey(user.getId()) ) {
                        throw new IllegalStateException(user.getId() + "this user id already exists in repo");
                    }
                    else {
                        this.users.put(user.getId(), user);
                        if  (lastId < user.getId()) {
                            lastId = user.getId();
                        }
                    }

                    this.userLinesMap.put(user.getLineId(), user);
                }
        );

    }

    public User createUser(String name, String email, String password) {
        Integer userId = ++this.lastId;
        User newUser = new User();
        newUser.setName(name);
        newUser.setId(userId);
        newUser.setEmail(email);
        newUser.setPassword(password);
        this.users.put(userId, newUser);
        this.file.appendLine(userId.toString() +","+name+","+email+","+password);
        return newUser;
    }
//      TODO метод method should return list
    public Map<Integer, User> getRepoList() {
        return this.users;
    }

    public User getUserById(Integer userId) {
        return this.users.get(userId);
    }

}