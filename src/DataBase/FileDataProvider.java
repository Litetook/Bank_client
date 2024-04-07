package DataBase;

public class FileDataProvider {

    private String filePath;
    public FileDataProvider(String table) {
//        load file
        this.filePath = "./Table/" + table.toLowerCase() + ".csv";
    }


}
