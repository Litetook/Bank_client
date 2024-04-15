package com.pragmatic.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BaseModel {

//     new version, перепитати як краще
//        public String[] getSchema2() {
//            return Arrays.stream(this.getClass().getDeclaredFields())
//                    .map(Field::getName)
//                    .toArray(String[]::new);
//        }

    public List<String> getSchema() {

        List<String> resultList = new ArrayList<String>();

        for (Field declaredField : this.getClass().getDeclaredFields()) {
            resultList.add(declaredField.getName());
        }
        return resultList;

    }
}
