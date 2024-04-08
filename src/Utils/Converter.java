package Utils;

import java.awt.desktop.SystemEventListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Converter {
    public static <T> String toCsvLine(T object, String schema) {
//        Це загальний метод для того щоб конвертувати об'єкт в csv лінію
//         T - це типу як клас, який може бути будь-яким, щоб на його основі зробити csv.
//        Наприклад каренсі або юзеh



        // schema
        // id,name,email,password

        // object
        // { id: 12, name: "John", email: "jo@mail.com", password: "12345" }

        // out
        // 12,John,jo@mail.com,12345



        List<String> columns = Arrays.asList(schema.split("\\s*,\\s*"));

        System.out.println(object.toString());



        for (Integer i =0; i < columns.size(); i++) {
            String key = columns.get(i);
            System.out.println("Key name:" + key);
            Method[] objMethods = object.getClass().getDeclaredMethods();
            System.out.println(objMethods);

            String methodSimilarName = "get" +  key;
            System.out.println("Started to analyze methods");

            for (int j = 0; j < objMethods.length ; j++) {
                if (objMethods[j].getName().contains(key)) {

                    System.out.println(objMethods[j].getName());

                    System.out.println(object


//                  object.getClass().getMethod(objMethods[j].getName())
                }


            }
            


//            dictRow.put(key, object.key )
        }




        return "";
    }
}
