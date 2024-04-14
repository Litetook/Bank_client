package dao;

import model.User;
import repository.UserRepository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;



public class Converter {
    private static final Map<String, Class<?>> fileToClassMap = new HashMap<>();


    public static <T> String toCsvLine(T object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fields = object.getClass().getDeclaredFields();
        Integer fieldsNumber = fields.length;
        String csvString = "";
        for (Integer i = 0; i < fieldsNumber; i++) {
            System.out.println(fieldsNumber + " " + " index " + i);
            Field field = fields[i];
            fields[i].setAccessible(true);
            System.out.println(field.get(object).toString());
            csvString += field.get(object).toString();
            if (i + 1 != fieldsNumber) {
                csvString += ",";
            }
            fields[i].setAccessible(false);
        }
        System.out.println(csvString);
        return csvString;

    }

////просто інстанс юзера. Переписати на репозиторій кастомного файла
//    public static void  createObjFromCsvLine(String fileName, String csvString) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        List<String> csvValues = Arrays.asList(csvString.split(","));
//        Class<?> inputClass = fileToClassMap.get(fileName);
//
//        java.lang.reflect.Constructor<?> constructor = inputClass.getConstructor(List.class);
//        Object instance = constructor.newInstance((Object) csvValues);
//
//    }

    public static void createRepoObjFromCsvLine(String csvString,  Object repoInstance) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<String> csvValues = Arrays.asList(csvString.split(","));
//        Оновити репозиторій базуючись на дані інстансів, що ми даємо.

//        Method[] methods = repoInstance.getClass().getDeclaredMethods();
//        for (Method method : methods) {
//
//            if (method.getName().equals("updateRepo")) {
//                try {
//                    method.invoke(repoInstance, csvValues);
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//      оновлення репо по інстансу
        repoInstance.getClass().getMethod("updateRepo", List.class).invoke(repoInstance, csvValues);


    }



//    public static <T> Object fromCsvLine(String csvString, T instance) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        List<String> schema  = (List<String>) instance.getClass().getMethod("getSchema").invoke(instance);
////        Розпарсити csv строку в список
//
//        List<String> csvValues = Arrays.asList(csvString.split(","));
//
//        Field[] instanceFields = instance.getClass().getDeclaredFields();
//
//        for (Integer i = 0;  i < instanceFields.length ; i++) {
//            instanceFields[i].setAccessible(true);
//            for (String value : csvValues) {
//
//                Class<?> type =  instanceFields[i].getType();
//
//                switch(type.toString()) {
//                    case  String.class:
//                        // code block
//                        instanceFields[i].set(instance, value);
//                        break;
//                    case Integer.class:
//                        // code block
//                        instanceFields[i].set(instance, Integer.parseInt(value));
//                        break;
//                    case Double.class:
//                        // code block
//                        instanceFields[i].set(instance, Double.parseDouble(value));
//                        break;
//                    case Float.class:
//                        instanceFields[i].set(instance, Float.parseFloat(value));
//                        break;
//                    case Long.class:
//                        instanceFields[i].set(instance, Long.parseLong(value));
//                        break;
//                    case LocalDate.class:
//                        instanceFields[i].set(instance, LocalDate.parse(value));
//                        break;
//                    default:
//                }
//
//                instanceFields[i].set(instance, type.cast(value));
//            }
//            instanceFields[i].setAccessible(false);
//
//        }
//
//
//
////        String[] array =  Arrays.stream(instance.getClass().getDeclaredFields())
////                .map(Field::getName)
////                .toArray(String[]::new);
////        System.out.println(array[1]);
//        return  instance;
//    }







}



//    public  static <T> String fromCsvLine(String tablename String line, String header) {
//   назва файлу, шапка файлу, і стрінга. Кожен елемент шапки має відповідати атрибутів об'єкту. Має створювати об'єкт
//   Отримує на прийом стрінгу, яку має обробити і віддати назад об'єкт вхідний
//        Тут мають бути можливі варіанти того, які мені об'єкти створювати. Давай почнемо з юзера
//        Не знаю точно, як приймати хедер. І де його описувати.
//        Нам треба розуміти ім'я класа і його дані, формат в яких воно все буде лежати. Наприклад це може бути
//    }

