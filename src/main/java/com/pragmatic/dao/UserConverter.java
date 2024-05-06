package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserConverter {
    public static List<String> csvSchema = new ArrayList<>(Arrays.asList("id", "name", "email","password"));
    public static User parseUser(String data) {
        String[] values = data.split(",");

        if (values.length != 4) {
            throw new IllegalArgumentException("Invalid user data");
        }

        User user = new User();
        user.setId(Integer.valueOf(values[0]));
        user.setName(values[1]);
        user.setEmail(values[2]);
        user.setPassword(values[3]);

        return  user;
    }

    public static String userFileDataCreator(List<User> userList) {
        StringBuilder string = new StringBuilder();
//       HEADER
        string.append(String.join(",", csvSchema));
        if (userList.size() > 0) {
            string.append("\n");
        }
        for (int i = 0; i <= userList.size() -1; i++) {
            User user = userList.get(i);
            string.append(String.join(",", user.getId().toString(), user.getName(), user.getEmail(), user.getPassword(), "\n"));
        }
        return  string.toString();
    }


}
