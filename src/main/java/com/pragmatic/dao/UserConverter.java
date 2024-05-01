package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.User;
import main.java.com.pragmatic.repository.UserRepository;

public class UserConverter {
    public static User parseUser(String data, Integer lineId) {
        String[] values = data.split(",");
        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid user data");
        }
        User user = new User();
        user.setId(Integer.valueOf(values[0]));
        user.setName(values[1]);
        user.setEmail(values[2]);
        user.setPassword(values[3]);
        user.setLineId(lineId);
        return  user;
    }
//    TODO HOW TO ADD AND MAKE IT WORK ACROSS ALL REPO
    public static String createUserCsvLine() {
//        return  "";
        String.format("%s, %s, %s, %d",  )
    }
}
