package com.pragmatic.testing;


public class Database {

    public Database() {
        // Публічний конструктор без аргументів
    }
    public void printLog(String message) {
        System.out.println("Saving log to database: " + message);
    }
    public String returnTest() {
        return "Test";
    }
}
