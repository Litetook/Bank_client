package repository;

import dao.FileDataProvider;
import model.User;

import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private static String tableImportName = "users.csv";
    private Integer lastId = 0;
    private HashMap<Integer,User> users;

    public UserRepository() {
        this.users = new HashMap<>();
//        Init даних з файлу
        new FileDataProvider(tableImportName).initData(this);

    }

    public User updateRepo(List<String> csvLine) {
        this.lastId ++;
        User newUser = new User(csvLine);
        this.users.put(this.lastId, newUser);
        return newUser;
    }

    public User createUser(String name, String email, String password) {
        this.lastId++;
        User newUser  = new User(name, email, password, this.lastId );
        this.users.put(this.lastId, newUser); //передаю ключ як порядковий номер, і значення як еземляр класа ( об'єкт)
//        write to file
        return newUser;
    }

    public HashMap<Integer, User> getUserList (){
        return  this.users;
    }
}
