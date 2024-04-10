//import User.User;


import DataBase.FileDataProvider;

public class Main {
    public  static void main(String[] args) {

        FileDataProvider dataProvider = new FileDataProvider("users.csv");

//        dataProvider.writeFile("user test write");

//        dataProvider.readFile();

//        String line = dataProvider.readLine(-10);
//
//        if(line.isEmpty()) {
//            // logic
//            System.out.println("ERROR: bad line number");
//        }
//
//        System.out.println("Line:" + line);

        User cli = new User("John", "jo@mail.com", "12345", 2);

        Converter.<User>toCsvLine(cli);


    }
}

