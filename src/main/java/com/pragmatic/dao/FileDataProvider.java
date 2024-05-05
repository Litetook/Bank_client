package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Currency;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.model.User;

import java.io.File;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


public class FileDataProvider  {
    private final String filePath;
    private final String table;
    private final File file;

    public FileDataProvider(String table) {
//        load file
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main" , "java", "com", "pragmatic", "dao", "tables", table.toLowerCase());
//        C:\Users\vitalii.tsomyk\Desktop\Bank_client\src\main\java\com\pragmatic\dao\tables
        this.filePath = path.toString();
        System.out.println("Initiated: ");
        System.out.println(this.filePath);
        File f = new File(this.filePath);

//        перевірка на існування файла і його створення, якщо що. Можна ремувнути на майбутнє, і
//        кидати ексепшен на запуск коду, якщо файлу нема.
        if  (!f.exists()) {
            try {
                f.createNewFile();
            }
            catch (Exception e) {
                System.out.println("Can not create destination file");
            }

            System.out.println("table has been created with name: " + table.toLowerCase());
        }
        else  {
            //If file exists, no comments to output
        }

        this.table = table;

        this.file = f;
    }


    public <T> List<T> readData(File file, Function<String, T> parseFunction) throws FileNotFoundException {
        List<T> dataList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header line
            }
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dataList.add(parseFunction.apply(data));
            }
        }
        return dataList;
    }

    public List<User> initUserData() {
        try {
            return readData(this.file, UserConverter::parseUser);
        } catch (Exception e) {
            System.out.println("User parse exception: " + e.getMessage());
            return null;
        }
    }

    public List<Currency> initCurrencyData() {
        try {
            return readData(this.file, CurrencyConverter::parseCurrency);
        } catch (Exception e) {
            System.out.println("Currency parse exception: " + e.getMessage());
            return null;
        }
    }

    public List<Account> initAccountData() {
        try {
            return readData(this.file, AccountConverter::parseAccount);
        } catch (Exception e) {
            System.out.println("Account parse exception: " + e.getMessage());
            return null;
        }
    }

    public List<Transaction> initTransactionData() {
        try {
            return readData(this.file, TransactionConverter::parseTransaction);
        } catch (Exception e) {
            System.out.println("Transaction parse exception: " + e.getMessage());
            return null;
        }
    }

    public File getFileObj() {
        return this.file;
    }





}
