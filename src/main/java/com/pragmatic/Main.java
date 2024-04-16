package main.java.com.pragmatic;//import main.java.com.pragmatic.model.User;


import main.java.com.pragmatic.repository.UserRepository;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        //      Викликаю юзер репозиторій, який парсить файл одразу і створює в ньому об'єкти юзера по cs
        UserRepository userRepo = new UserRepository();
        System.out.println(userRepo.getUserList());
        userRepo.createUser("manual", "blabla", "pass");
        System.out.println(userRepo.getUserList());
    }
}

