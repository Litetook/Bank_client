package User;

import DataBase.DataProvider;

import java.util.HashMap;

public class UserRepository {

    private HashMap<Integer,User> users;

    private Integer lastId;

    public UserRepository(DataProvider) {
//        data provider
//        load users
    this.users = new HashMap<Integer, User>();

    }


    public User createUser(String name, String email, String password) {
        this.lastId++;
        User newUser  = new User(name, email, password, this.lastId );
        this.users.put(this.lastId, newUser);
//        write to file
        return newUser;
    }
}
