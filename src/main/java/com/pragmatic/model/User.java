package com.pragmatic.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {

    private Integer id;
    private String name ;
    private String email;
    private String password;

//    private final String[] schema = {"id", "name", "email", "password"}; // should be hash map

    //    Account[] accounts


    public User(String name, String email, String password, Integer id) {
        this.id = id;
        this.name = name;
        this.email= email;
        this.setPassword(password);

    }

    public User(List<String> attributes) {
        this.id = Integer.parseInt(attributes.get(0));
        this.name = attributes.get(1);
        this.email = attributes.get(2);
        this.password = attributes.get(3);
    }


// new version, перепитати як краще
//    public String[] getSchema2() {
//        return Arrays.stream(this.getClass().getDeclaredFields())
//                .map(Field::getName)
//                .toArray(String[]::new);
//    }

    public List<String> getSchema() {

        List<String> resultList = new ArrayList<String>();

        for (Field declaredField : this.getClass().getDeclaredFields()) {
            resultList.add(declaredField.getName());
        }
        return resultList;

    }



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

