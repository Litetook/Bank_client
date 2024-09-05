package com.pragmatic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer id;
    private String name ;
    private String email;
    private String password;
    private List<Account> accounts = new ArrayList<>();


    public User(String name, String email, String password, Integer id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.setPassword(password);

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

