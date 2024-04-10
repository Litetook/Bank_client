package Utils;

import java.awt.desktop.SystemEventListener;
import java.lang.reflect.Method;
import java.util.*;


public class Converter {
    public static <T> String toCsvLine(T object) {
//        Це загальний метод для того щоб конвертувати об'єкт в csv лінію
//         T - це типу як клас, який може бути будь-яким, щоб на його основі зробити csv.
//        Наприклад каренсі або юзеh

        // schema
        // id,name,email,password

        // object
        // { id: 12, name: "John", email: "jo@mail.com", password: "12345" }

        // out
        // 12,John,jo@mail.com,12345


        Method[] methods = object.getClass().getDeclaredMethods();

        for (Method method: methods) {
//            String method_name = method.getName();

            if (method.getName().equals("getSchema")) {
                try {
//                   список ключів
                    String[] result = (String[]) method.invoke(object);
//                    System.out.println(result[0]);


                    String csvString = "";
                    for (String key: result) {
                        try {
                            String value =  object.getClass().getMethod(
                                    "get" + Character.toUpperCase(key.charAt(0)) + key.substring(1)
                            ).invoke(object).toString();

                            System.out.println(key + " : " + value);

                            csvString += value+",";


                        }
                        catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }

                    csvString = csvString.substring(0, csvString.length() -1);

                    System.out.println(csvString);

                    return csvString;


                }
                catch (Exception e) {
                    System.out.println("method getSchema dynamic execution error");
                }
            }

        }








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



        return "";
    }
}
