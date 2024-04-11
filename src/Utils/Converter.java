package Utils;

import java.awt.desktop.SystemEventListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.*;


public class Converter {
    public static <T> String toCsvLine(T object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Це загальний метод для того щоб конвертувати об'єкт в csv лінію
//         T - це типу як клас, який може бути будь-яким, щоб на його основі зробити csv.
//        Наприклад каренсі або юзеh
//       Вкинуті сюди інстанси мають мати метод getSchema
        // schema
        // id,name,email,password

        // object
        // { id: 12, name: "John", email: "jo@mail.com", password: "12345" }

        // out
        // 12,John,jo@mail.com,12345

//        Визначаємо схему, яка має бути в csv. І це має бути інтерфейс в майбутньому
//        Method[] methods = object.getClass().getDeclaredMethods();


        Field[] fields = object.getClass().getDeclaredFields();

        Integer fieldsNumber = fields.length;

        String csvString = "";



        for (Integer i =0; i < fieldsNumber; i++) {
//            System.out.println(field);
//           Витягую значення філда

            System.out.println(fieldsNumber + " " + " index "+ i);

            Field field = fields[i];

            fields[i].setAccessible(true);

            System.out.println( field.get(object).toString());

            csvString+= field.get(object).toString();
            if (i+1 != fieldsNumber ) {
                csvString+= ",";
            }

            fields[i].setAccessible(false);

        }
        System.out.println(csvString);
        return  csvString;










//        System.out.println(object.toString());


//        for (Integer i =0; i < columns.size(); i++) {
//            String key = columns.get(i);
//            System.out.println("Key name:" + key);
//            Method[] objMethods = object.getClass().getDeclaredMethods();
//            System.out.println(objMethods);
//
//            String methodSimilarName = "get" +  key;
//            System.out.println("Started to analyze methods");
//
//            for (int j = 0; j < objMethods.length ; j++) {
//                if (objMethods[j].getName().contains(key)) {
//
//                    System.out.println(objMethods[j].getName());
//
//                    System.out.println(object);
//
//
////                  object.getClass().getMethod(objMethods[j].getName())
//                }
//
//
//            }
//


//            dictRow.put(key, object.key )
//        }



//        return "";
    }
}

//    public  static <T> String fromCsvLine(String line, ArrayList<String> header) {
//   Отримує на прийом стрінгу, яку має обробити і віддати назад об'єкт вхідний
//        Тут мають бути можливі варіанти того, які мені об'єкти створювати. Давай почнемо з юзера
//        Не знаю точно, як приймати хедер. І де його описувати.
//        Нам треба розуміти ім'я класа і його дані, формат в яких воно все буде лежати. Наприклад це може бути
//    }
