package com.pragmatic.testing;

public class Logger {
    private Database database;

    public Logger(Database database) {
        this.database = database;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
        database.printLog(message);
    }

    public String getAdditionalData() {
        return "Some additional data";
    }
}