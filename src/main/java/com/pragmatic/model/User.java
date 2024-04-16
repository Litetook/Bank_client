package main.java.com.pragmatic.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {

    private Integer id;
    private String name ;
    private String email;
    private String password;

    private List<Account> accounts;



    public User(String name, String email, String password, Integer id) {
        this.id = id;
        this.name = name;
        this.email= email;
        this.setPassword(password);

    }

    public User() {
    }


// new version, перепитати як краще
//    public String[] getSchema2() {
//        return Arrays.stream(this.getClass().getDeclaredFields())
//                .map(Field::getName)
//                .toArray(String[]::new);
//    }

//    public List<String> getSchema() {
//
//        List<String> resultList = new ArrayList<String>();
//
//        for (Field declaredField : this.getClass().getDeclaredFields()) {
//            resultList.add(declaredField.getName());
//        }
//        return resultList;
//
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

    public void setPassword(String password) {
//        hash user
        this.password = password;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

