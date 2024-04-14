//import model.User;


import dao.FileDataProvider;
import dao.Converter;
import model.User;
import repository.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

//        тут список файлів. Для кожного файла дьоргаємо його ініт.
//        тільки по юзеру поки роблю
//        Переписати щоб по юзер репозиторію в конструкторі дьоргало файл
        UserRepository userRepo = new UserRepository();

        FileDataProvider userDataProvider = new FileDataProvider("users.csv");
        userDataProvider.initData(userRepo);
        System.out.println(userRepo.getUserList());




        //        User cli = new User("John", "jo@mail.com", "password", 2);

//        System.out.println(Converter.fromCsvLine("", cli));
//        cli.getSchema();


    }
}

