package main.java.com.pragmatic.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class BaseModel implements IModel {
    Integer lineId;

    @Override
    public Integer getLineId() {
        return this.lineId;
    }

    @Override
    public void setLineId(Integer lineNumber) {
        this.lineId= lineNumber;
    }
}
