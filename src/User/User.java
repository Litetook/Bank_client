package User;

import Account.Account;

public class User {

    private Integer id;
    private String name ;
    private String email;
    private String password;

//    private final String[] schema = {"id", "name", "email", "password"}; // should be hash map

    //    Account[] accounts


    public User(String name, String email, String password, Integer id) {
        this.name = name;
        this.email= email;
        this.id = id;
        this.setPassword(password);


    }


//    public String[] getSchema() {
//        return this.schema;
//    }

    public String getName() {
        return  this.name;
    }

    public  Integer getId() {
        return this.id;
    }

    public String getPassword() {
        return  this.password;
    }

    public String getEmail() {
        return this.email;
    }

    private void setPassword(String password) {
//        hash user
        this.password = password;

    }
}

