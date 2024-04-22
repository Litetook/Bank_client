package main.java.com.pragmatic.repository;


import main.java.com.pragmatic.dao.FileDataProvider;
import main.java.com.pragmatic.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository implements IRepository {
    private static String tableImportName = "users.csv";
    private Integer lastId = 0;
    private Map<Integer, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
//        Init даних з файлу
        List<User> userList = new FileDataProvider(tableImportName).initUserData();
        userList.forEach(user -> {
                    if ( this.users.keySet().contains(user.getId()) ) {
                        new Exception(user.getId() + "this user id already exists in repo");
                    }
                    else {
                        this.users.put(user.getId(), user);
                        if  (lastId < user.getId()) {
                            lastId = user.getId();
                        }
                    }

                }
        );

    }

    //   Подумати чи ок зроблено.
    public User createUser(String name, String email, String password) {
        Integer userId = ++this.lastId;
        User newUser = new User();
        newUser.setName(name);
        newUser.setId(userId);
        newUser.setEmail(email);
        newUser.setPassword(password);

        this.users.put(userId, newUser); //передаю ключ як порядковий номер, і значення як еземляр класа ( об'єкт)
//        write to file
        return newUser;
    }

    public Map<Integer, User> getRepoList() {
        return this.users;
    }

    public User getUserById(Integer userId) {
        return this.users.get(userId);
    }
}