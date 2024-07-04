package com.pragmatic.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String name ;
    private String email;
    private String password;
    private List<Account> accounts = new ArrayList<>();


    public User(String name, String email, String password, Integer id) {
        this.id = id;
        this.name = name;
        this.email= email;
        this.setPassword(password);

    }

    public User() {
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

    public void addAccount(Account account) {
        try {
            this.accounts.add(account);
        }
        catch (Exception e) {
            System.out.println("user add account error. Userid: " + this.getId());
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }


    @Override
    public String toString() {
        return "User{" +
                "accounts=" + accounts +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name +
                '}';
    }
}

