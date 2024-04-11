package User;

//import DataBase.DataProvider;

import java.util.HashMap;

public class UserRepository {

    private HashMap<Integer,User> users;


    private Integer lastId;

    public UserRepository() {
//        запихнути в юзера дані з дата провайдера
//        data provider
//        load users
        this.users = new HashMap<>();

    }


    public User createUser(String name, String email, String password) {
        this.lastId++;
        User newUser  = new User(name, email, password, this.lastId );
        this.users.put(this.lastId, newUser); //передаю ключ як порядковий номер, і значення як еземляр класа ( об'єкт)
//        write to file
        return newUser;
    }
}
