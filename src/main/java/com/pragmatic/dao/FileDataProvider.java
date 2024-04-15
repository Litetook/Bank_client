package com.pragmatic.dao;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileDataProvider {
    private final String filePath;

    private final File file;

    public FileDataProvider(String table) {
//        load file
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main" , "java", "com", "pragmatic", "dao", "tables", table.toLowerCase());
//        C:\Users\vitalii.tsomyk\Desktop\Bank_client\src\main\java\com\pragmatic\dao\tables
        this.filePath = path.toString();
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
            System.out.println("Input table exists");
        }

        this.file = f;
    }


    public void  initData(Object repoInstance) {
//         у конвертер ми передаємо рядок шапки.
        try (Scanner reader = new Scanner(this.file)) {
//            пропускаємо перший рядок, бо це шапка
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
//                Converter.createObjFromCsvLine(this.file.getName(), data);
                  Converter.createRepoObjFromCsvLine(data, repoInstance);
//                System.out.println(this.file.getName());
                System.out.println("row");
                System.out.println(data);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Scanner file reader exception. File not found");
        }
    }


    public Boolean writeFile(String content) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write("\n" + content);

            System.out.println("Success file write");

            writer.close();


//           результат успнішного закінчення методу і запису в файл
            return true;
        }
        catch (IOException e) {
            System.out.println("There is no output file created");
        }
        return  false;
    }

    public  String readLine(Integer lineNumber) {
        if (lineNumber >= 1) {
            try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
                return lines.skip(lineNumber - 1).findFirst().get();
            } catch (Exception e) {
                System.out.println("Read file exception.");

            }
        }
        return "";
    }

}
