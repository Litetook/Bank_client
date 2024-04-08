package User;

import Account.Account;

public class User {

    Integer id;
    String name ;
    String email;
    String password;

    //    Account[] accounts


    public User(String name, String email, String password, Integer id) {
        this.name = name;
        this.email= email;
        this.id = id;
        this.setPassword(password);


    }

    public String getName() {
        return  this.name;
    }

    public String getEmail() {
        return this.email;
    }

    private void setPassword(String password) {
//        hash user
        this.password = password;

    }
}

