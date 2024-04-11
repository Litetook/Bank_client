//import User.User;


import Currency.Currency;
import DataBase.FileDataProvider;
import Utils.Converter;
import User.User;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public  static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        FileDataProvider dataProvider = new FileDataProvider("users.csv");

        User cli = new User("John", "jo@mail.com", "password", 2);

        Converter.<User>toCsvLine(cli);


    }
}

